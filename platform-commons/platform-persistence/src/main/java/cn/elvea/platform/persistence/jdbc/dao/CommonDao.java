package cn.elvea.platform.persistence.jdbc.dao;

import cn.elvea.platform.persistence.jdbc.dialect.Dialect;
import cn.elvea.platform.persistence.jdbc.utils.JdbcUtils;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * JDBC Common Dao
 *
 * @author elvea
 * @since 0.0.1
 */
public class CommonDao {

    private final JdbcTemplate jdbcTemplate;

    private final Dialect dialect;

    public CommonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        // 获取数据库方言
        this.dialect = this.jdbcTemplate.execute((ConnectionCallback<Dialect>) JdbcUtils::getDialect);
    }

    /**
     * 获取数据库系统当前时间
     *
     * @return 数据库系统当前时间
     */
    public LocalDateTime getCurrentLocalDateTime() {
        return this.jdbcTemplate.queryForObject(this.dialect.buildCurrentDateTimeSql(), java.time.LocalDateTime.class);
    }

    /**
     * 获取数据库系统当前时间
     *
     * @return 数据库系统当前时间
     */
    public LocalDate getCurrentLocalDate() {
        return this.jdbcTemplate.queryForObject(this.dialect.buildCurrentDateSql(), java.time.LocalDate.class);
    }

    /**
     * 获取数据库系统当前时间
     *
     * @return 数据库系统当前时间
     */
    public LocalTime getCurrentLocalTime() {
        return this.jdbcTemplate.queryForObject(this.dialect.buildCurrentTimeSql(), java.time.LocalTime.class);
    }

}
