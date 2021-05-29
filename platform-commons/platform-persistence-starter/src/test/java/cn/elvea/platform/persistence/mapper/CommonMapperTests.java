package cn.elvea.platform.persistence.mapper;

import cn.elvea.platform.persistence.PersistenceTestApplicationTests;
import cn.elvea.platform.persistence.mybatis.mapper.CommonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * CommonMapperTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class CommonMapperTests extends PersistenceTestApplicationTests {

    @Autowired
    CommonMapper commonMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(commonMapper);

        LocalDateTime currentLocalDateTime = this.commonMapper.getCurrentLocalDateTime();
        Assertions.assertNotNull(currentLocalDateTime);

        LocalDate currentLocalDate = this.commonMapper.getCurrentLocalDate();
        Assertions.assertNotNull(currentLocalDate);

        LocalTime currentLocalTime = this.commonMapper.getCurrentLocalTime();
        Assertions.assertNotNull(currentLocalTime);

    }

}
