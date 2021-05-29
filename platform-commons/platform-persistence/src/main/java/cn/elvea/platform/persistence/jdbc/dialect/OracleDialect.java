package cn.elvea.platform.persistence.jdbc.dialect;

/**
 * OracleDialect
 *
 * @author elvea
 * @since 0.0.1
 */
public class OracleDialect implements Dialect {

    /**
     * @see Dialect#buildCreateSimpleTemporaryTableSql(String)
     */
    @Override
    public String buildCreateSimpleTemporaryTableSql(String temporaryTableName) {
        return String.format("create global temporary table %s (id number(19)) on commit preserve rows ", temporaryTableName);
    }

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
