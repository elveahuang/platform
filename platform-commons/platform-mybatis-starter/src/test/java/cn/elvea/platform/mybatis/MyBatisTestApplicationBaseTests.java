package cn.elvea.platform.mybatis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * MyBatisTestApplicationBaseTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class MyBatisTestApplicationBaseTests extends MyBatisTestApplicationTests {

    protected final DataSource dataSource;

    @Autowired
    protected MyBatisTestApplicationBaseTests(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    public void test() {
        Assertions.assertNotNull(this.dataSource);
    }

}
