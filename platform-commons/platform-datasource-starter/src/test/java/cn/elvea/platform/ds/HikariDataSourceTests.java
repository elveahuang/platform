package cn.elvea.platform.ds;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

/**
 * 传统数据源单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("hikari")
public class HikariDataSourceTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void baseTest() {
        DataSource dataSource = this.jdbcTemplate.getDataSource();
        Assertions.assertTrue(dataSource instanceof HikariDataSource);
    }

}
