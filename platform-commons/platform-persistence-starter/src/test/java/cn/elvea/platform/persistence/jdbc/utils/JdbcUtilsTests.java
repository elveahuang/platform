package cn.elvea.platform.persistence.jdbc.utils;

import cn.elvea.platform.commons.id.IdWorker;
import cn.elvea.platform.persistence.PersistenceTestApplicationTests;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * JdbcUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class JdbcUtilsTests extends PersistenceTestApplicationTests {

    @Autowired
    IdWorker idWorker;

    @Autowired
    DataSource dataSource;

    @Test
    public void createTemporaryTableTest() throws SQLException {
        String temporaryTableName = null;
        Connection connection = null;
        try {
            List<Long> idList = Lists.newArrayList();
            for (int i = 0; i < 1234567; i++) {
                idList.add(idWorker.nextId());
            }
            System.out.printf(" list - %s%n", idList.size());
            connection = this.dataSource.getConnection();
            long startTime = System.currentTimeMillis();
            temporaryTableName = JdbcUtils.createTemporaryTable(connection, idList);
            long endTime = System.currentTimeMillis();
            System.out.printf(" time - %s%n", endTime - startTime);
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(String.format("select count(id) count from %s", temporaryTableName));
                if (rs.next()) {
                    System.out.printf(" db - %s%n", rs.getInt("count"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (StringUtils.isNotEmpty(temporaryTableName)) {
                JdbcUtils.dropTemporaryTable(connection, temporaryTableName);
            }
            JdbcUtils.close(connection);
        }
    }

}
