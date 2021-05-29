package cn.elvea.platform.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

/**
 * DataSourceTestApplicationTests
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DataSourceTestApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextTest() {
        Assertions.assertNotNull(dataSource);
    }

}
