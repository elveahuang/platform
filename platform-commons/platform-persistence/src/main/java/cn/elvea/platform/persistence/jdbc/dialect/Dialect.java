package cn.elvea.platform.persistence.jdbc.dialect;

/**
 * DatabaseDialect
 *
 * @author elvea
 * @since 0.0.1
 */
public interface Dialect {

    /**
     * 生成创建简单临时表的语句
     *
     * @param temporaryTableName 表名
     * @return 查询语句
     */
    default String buildCreateSimpleTemporaryTableSql(String temporaryTableName) {
        return String.format("create table %s (id bigint); ", temporaryTableName);
    }

    /**
     * 生产查询总记录数的语句
     *
     * @param originalSql 原始SQL
     * @return 查询语句
     */
    default String buildCountSql(String originalSql) {
        return String.format(" SELECT COUNT(1) FROM ( %s ) TOTAL ", originalSql);
    }

    /**
     * 生成分页查询语句
     *
     * @param originalSql 查询语句
     * @param offset      偏移值
     * @param limit       记录数
     * @return 查询语句
     */
    String buildPaginationSql(String originalSql, long offset, long limit);

    /**
     * 生成查询当前时间语句
     */
    String buildCurrentDateTimeSql();

    /**
     * 生成查询当前时间语句
     */
    String buildCurrentDateSql();

    /**
     * 生成查询当前时间语句
     */
    String buildCurrentTimeSql();

}
