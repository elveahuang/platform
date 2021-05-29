package cn.elvea.platform.persistence.mybatis.interceptor;

import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.persistence.jdbc.DbType;
import cn.elvea.platform.persistence.jdbc.dialect.Dialect;
import cn.elvea.platform.persistence.jdbc.utils.JdbcUtils;
import cn.elvea.platform.persistence.mybatis.mapping.BoundSqlSqlSource;
import cn.elvea.platform.persistence.mybatis.page.PageList;
import cn.elvea.platform.persistence.mybatis.utils.MyBatisUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * PaginationInterceptor
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Setter
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class PaginationInterceptor implements Interceptor {

    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 数据库放眼
     */
    private Dialect dbDialect;

    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
        Object parameterObject = invocation.getArgs()[PARAMETER_INDEX];

        Executor executor = MyBatisUtils.getRealTarget(invocation.getTarget());

        // 查询操作或者存储过程，无需分页
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType() || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }

        // 是否分页查询
        Pageable pageable = MyBatisUtils.findPageable(parameterObject);
        if (pageable == null) {
            return invocation.proceed();
        }
        // 是否需要统计总记录数
        boolean queryTotalCount = MyBatisUtils.isReturnTypePageList(invocation);

        Connection connection = executor.getTransaction().getConnection();

        DbType dbType = this.dbType == null ? JdbcUtils.getDbType(connection) : this.dbType;
        Dialect dialect = Optional.ofNullable(this.dbDialect).orElseGet(() -> JdbcUtils.getDialect(dbType));

        // 针对定义了rowBounds，做为mapper接口方法的参数
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        String originalSql = boundSql.getSql();

        // 查询总记录数
        long total = 0;
        if (queryTotalCount) {
            String countSql = dialect.buildCountSql(boundSql.getSql());
            log.debug("current count sql - {}", countSql);
            total = this.queryTotal(countSql, mappedStatement, boundSql, connection);
        }

        String buildSql = concatOrderBy(originalSql, pageable);
        String paginationSql = dialect.buildPaginationSql(buildSql, pageable.getOffset(), pageable.getPageSize());
        log.debug("current pagination sql - {}", paginationSql);
        BoundSql newBs = MyBatisUtils.copyFromBoundSql(mappedStatement, boundSql, paginationSql, parameterObject);
        MappedStatement newMs = MyBatisUtils.copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBs));
        args[MAPPED_STATEMENT_INDEX] = newMs;
        args[ROWBOUNDS_INDEX] = RowBounds.DEFAULT;

        Object result = invocation.proceed();
        if (queryTotalCount) {
            return new PageList<>((List<?>) result, pageable, total);
        } else {
            return result;
        }
    }

    /**
     * 查询总记录条数
     *
     * @param sql             SQL
     * @param mappedStatement {@link MappedStatement}
     * @param boundSql        {@link BoundSql}
     * @param connection      {@link Connection}
     */
    protected long queryTotal(String sql, MappedStatement mappedStatement, BoundSql boundSql, Connection connection) {
        long total = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler.setParameters(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getLong(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 查询SQL拼接Order By
     *
     * @param originalSql SQL
     * @param pageable    {@link Pageable}
     * @return SQL
     */
    public String concatOrderBy(String originalSql, Pageable pageable) {
        if (CollectionUtils.isNotEmpty(pageable.getSort().toList())) {
            try {
                List<Sort.Order> orderList = pageable.getSort().toList();
                Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);
                if (selectStatement.getSelectBody() instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
                    List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
                    List<OrderByElement> orderByElementsReturn = addOrderByElements(orderList, orderByElements);
                    plainSelect.setOrderByElements(orderByElementsReturn);
                    return plainSelect.toString();
                } else if (selectStatement.getSelectBody() instanceof SetOperationList) {
                    SetOperationList setOperationList = (SetOperationList) selectStatement.getSelectBody();
                    List<OrderByElement> orderByElements = setOperationList.getOrderByElements();
                    List<OrderByElement> orderByElementsReturn = addOrderByElements(orderList, orderByElements);
                    setOperationList.setOrderByElements(orderByElementsReturn);
                    return setOperationList.toString();
                } else if (selectStatement.getSelectBody() instanceof WithItem) {
                    return originalSql;
                } else {
                    return originalSql;
                }

            } catch (JSQLParserException e) {
                log.warn("failed to concat orderBy from IPage, exception=" + e.getMessage());
            }
        }
        return originalSql;
    }

    private static List<OrderByElement> addOrderByElements(List<Sort.Order> orderList,
                                                           List<OrderByElement> orderByElements) {
        orderByElements = CollectionUtils.isEmpty(orderByElements) ? new ArrayList<>(orderList.size()) : orderByElements;
        List<OrderByElement> orderByElementList = orderList.stream().map(item -> {
            OrderByElement element = new OrderByElement();
            element.setExpression(new Column(item.getProperty()));
            element.setAsc(item.getDirection().isAscending());
            return element;
        }).collect(Collectors.toList());
        orderByElements.addAll(orderByElementList);
        return orderByElements;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
