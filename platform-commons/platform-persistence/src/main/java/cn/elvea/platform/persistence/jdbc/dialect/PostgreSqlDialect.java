package cn.elvea.platform.persistence.jdbc.dialect;

/**
 * PostgreSqlDialect
 *
 * @author elvea
 * @since 0.0.1
 */
public class PostgreSqlDialect implements Dialect {

    /**
     * @see Dialect#buildPaginationSql(String, long, long)
     */
    @Override
    public String buildPaginationSql(String originalSql, long offset, long limit) {
        return null;
    }

    @Override
    public String buildCurrentDateTimeSql() {
        return null;
    }

    @Override
    public String buildCurrentDateSql() {
        return null;
    }

    @Override
    public String buildCurrentTimeSql() {
        return null;
    }

}
