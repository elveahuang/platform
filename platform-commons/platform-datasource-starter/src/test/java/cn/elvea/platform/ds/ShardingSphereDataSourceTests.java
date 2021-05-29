package cn.elvea.platform.ds;

import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
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
 * 读写分离数据源单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("shardingsphere")
public class ShardingSphereDataSourceTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void baseTest() {
        DataSource dataSource = this.jdbcTemplate.getDataSource();
        Assertions.assertTrue(dataSource instanceof ShardingSphereDataSource);
    }

}
