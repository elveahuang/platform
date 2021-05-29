package cn.elvea.platform.persistence.jdbc.dialect;

/**
 * MySqlDialect
 *
 * @author elvea
 * @since 0.0.1
 */
public class MySqlDialect implements Dialect {

    /**
     * @see Dialect#buildPaginationSql(String, long, long)
     */
    @Override
    public String buildPaginationSql(String originalSql, long offset, long limit) {
        return originalSql.concat(" limit ").concat(String.valueOf(limit)).concat(", ").concat(String.valueOf(offset));
    }

    /**
     * @see Dialect#buildCurrentDateTimeSql()
     */
    @Override
    public String buildCurrentDateTimeSql() {
        return "select now();";
    }

    /**
     * @see Dialect#buildCurrentDateSql()
     */
    @Override
    public String buildCurrentDateSql() {
        return "select curdate();";
    }

    /**
     * @see Dialect#buildCurrentTimeSql()
     */
    @Override
    public String buildCurrentTimeSql() {
        return "select curtime();";
    }

}
