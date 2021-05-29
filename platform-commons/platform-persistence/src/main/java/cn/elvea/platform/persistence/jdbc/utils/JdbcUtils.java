package cn.elvea.platform.persistence.jdbc.utils;

import cn.elvea.platform.commons.exception.SystemException;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.persistence.jdbc.DbType;
import cn.elvea.platform.persistence.jdbc.dialect.*;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.sql.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JdbcUtils
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class JdbcUtils {

    public final static int DEFAULT_BATCH_SIZE = 2000;

    private final static Map<DbType, Dialect> dialectEnumMap = new EnumMap<>(DbType.class);

    static {
        dialectEnumMap.put(DbType.MYSQL, new MySqlDialect());
        dialectEnumMap.put(DbType.MARIADB, new MariaDbDialect());
        dialectEnumMap.put(DbType.POSTGRESQL, new PostgreSqlDialect());
        dialectEnumMap.put(DbType.ORACLE, new OracleDialect());
    }

    public static DatabaseMetaData getMetaData(Connection con) throws SQLException {
        Assert.notNull(con, "Connection must not be null.");
        return con.getMetaData();
    }

    public static DbType getDbType(Connection con) throws SQLException {
        Assert.notNull(con, "Connection must not be null.");
        return getDbType(getMetaData(con).getURL());
    }

    public static DbType getDbType(String connectionUrl) {
        Assert.notNull(connectionUrl, "Connection url must not be null.");
        String url = connectionUrl.toLowerCase();
        if (url.contains(":mysql:")) {
            return DbType.MYSQL;
        } else if (url.contains(":mariadb:")) {
            return DbType.MARIADB;
        } else if (url.contains(":oracle:")) {
            return DbType.ORACLE;
        } else if (url.contains(":postgresql:")) {
            return DbType.POSTGRESQL;
        } else {
            log.warn("The jdbc url is {}, Cannot get Database type or The Database is Not Supported!", connectionUrl);
            return DbType.UNKNOWN;
        }
    }

    public static Dialect getDialect(Connection con) throws SQLException {
        return getDialect(getDbType(getMetaData(con).getURL()));
    }

    public static Dialect getDialect(DbType dbType) {
        return Optional.ofNullable(dialectEnumMap.get(dbType))
                .orElseThrow(() -> new SystemException(String.format("%s database not supported.", dbType.getCode())));
    }

    /**
     * 创建简单临时表
     */
    public static String createTemporaryTable(Connection con, List<Long> data) throws SQLException {
        // 生成临时表名
        String temporaryTableName = "tmp_" + ((int) (Math.random() * 100000)) + "_" + System.currentTimeMillis();

        Statement statement = null;
        try {
            // 获取数据库类型
            Dialect dialect = getDialect(getDbType(con));

            // 创建临时表
            statement = con.createStatement();
            statement.execute(dialect.buildCreateSimpleTemporaryTableSql(temporaryTableName));

            String sqlTpl = " insert into %s (id) values %s ";
            if (CollectionUtils.isNotEmpty(data)) {
                if (data.size() > JdbcUtils.DEFAULT_BATCH_SIZE) {
                    int i = data.size() / DEFAULT_BATCH_SIZE;
                    for (int k = 0; k < i; k++) {
                        List<Long> list = data.subList(k * DEFAULT_BATCH_SIZE, (k + 1) * DEFAULT_BATCH_SIZE);
                        String sql = String.format(sqlTpl, temporaryTableName, StringUtils.collectionToDelimitedString(list, ", ", "(", ")"));
                        statement.execute(sql);
                    }
                    if (data.size() % DEFAULT_BATCH_SIZE != 0) {
                        List<Long> list = data.subList(data.size() - (data.size() % DEFAULT_BATCH_SIZE), data.size());
                        String sql = String.format(sqlTpl, temporaryTableName, StringUtils.collectionToDelimitedString(list, ", ", "(", ")"));
                        statement.execute(sql);
                    }
                } else {
                    String sql = String.format(sqlTpl, temporaryTableName, StringUtils.collectionToDelimitedString(data, ", ", "(", ")"));
                    statement.execute(sql);
                }
            }
            return temporaryTableName;
        } catch (Exception e) {
            log.error("Create temporary table {} failure. ", temporaryTableName, e);
            throw new SQLException(String.format("Create temporary table %s failure.", temporaryTableName));
        } finally {
            close(statement);
        }
    }

    /**
     * 移除临时表
     *
     * @param temporaryTableName 临时表名
     */
    public static void dropTemporaryTable(Connection con, String temporaryTableName) throws SQLException {
        if (!Strings.isNullOrEmpty(temporaryTableName)) {
            Statement statement = null;
            try {
                statement = con.createStatement();
                statement.execute(String.format("truncate table %s", temporaryTableName));
                statement.execute(String.format("drop table %s", temporaryTableName));
            } catch (Exception e) {
                log.error("Drop temporary table {} failure.", temporaryTableName, e);
                throw new SQLException(String.format("Drop temporary table %s failure.", temporaryTableName));
            } finally {
                close(statement);
            }
        }
    }

    public static void close(ResultSet rs, Statement stmt) {
        close(rs);
        close(stmt);
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                log.error("Could not close JDBC Statement", ex);
            } catch (Throwable ex) {
                log.error("Unexpected exception on closing JDBC Statement", ex);
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC ResultSet", ex);
            } catch (Throwable ex) {
                log.trace("Unexpected exception on closing JDBC ResultSet", ex);
            }
        }
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                log.trace("Could not close JDBC Connection", ex);
            } catch (Throwable ex) {
                log.trace("Unexpected exception on closing JDBC Connection", ex);
            }
        }
    }

}
