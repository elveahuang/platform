package cn.elvea.platform.persistence.jdbc.dao;

import cn.elvea.platform.base.enums.TimeZoneEnum;
import cn.elvea.platform.commons.id.IdWorker;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.persistence.PersistenceTestApplicationTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static cn.elvea.platform.base.constants.DateTimeConstants.*;
import static cn.elvea.platform.base.constants.DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;

/**
 * CommonDaoTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class CommonDaoTests extends PersistenceTestApplicationTests {

    @Autowired
    CommonDao commonDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    IdWorker idWorker;

    @Test
    public void baseTests() {
        Assertions.assertNotNull(commonDao);

        LocalDateTime currentLocalDateTime = this.commonDao.getCurrentLocalDateTime();
        Assertions.assertNotNull(currentLocalDateTime);

        LocalDate currentLocalDate = this.commonDao.getCurrentLocalDate();
        Assertions.assertNotNull(currentLocalDate);

        LocalTime currentLocalTime = this.commonDao.getCurrentLocalTime();
        Assertions.assertNotNull(currentLocalTime);
    }

    @Test
    public void timeZoneTests() {
        this.jdbcTemplate.update("truncate sys_test");

        for (TimeZoneEnum tz : TimeZoneEnum.values()) {
            this.jdbcTemplate.update("insert into sys_test (id, code, description, datetime) values (?, ?, ?, ?)",
                    idWorker.nextId(), tz.getTitle(), tz.getDescription(), LocalDateTime.now(tz.getZoneId()));
        }

        this.jdbcTemplate.update("insert into sys_test (id, datetime, timestamp) values (?, ?, ?)",
                idWorker.nextId(), LocalDateTime.now(ZONE_ID_DEFAULT), LocalDateTime.now(ZONE_ID_DEFAULT));
        this.jdbcTemplate.update("insert into sys_test (id, datetime, timestamp) values (?, ?, ?)",
                idWorker.nextId(), LocalDateTime.now(ZONE_ID_CHINA), LocalDateTime.now(ZONE_ID_CHINA));
        this.jdbcTemplate.update("insert into sys_test (id, datetime, timestamp) values (?, ?, ?)",
                idWorker.nextId(), LocalDateTime.now(ZONE_ID_UTC), LocalDateTime.now(ZONE_ID_UTC));

        String now = DateUtils.format(new Date(), DEFAULT_DATE_TIME_PATTERN);
        Date dateCtt = DateUtils.parse(now, DEFAULT_DATE_TIME_PATTERN, TIME_ZONE_CHINA);
        Date dateUtc = DateUtils.parse(now, DEFAULT_DATE_TIME_PATTERN, TIME_ZONE_UTC);
        this.jdbcTemplate.update("insert into sys_test (id, code, description, datetime) values (?, ?, ?, ?)",
                idWorker.nextId(), "CTT", "CTT", dateCtt);
        this.jdbcTemplate.update("insert into sys_test (id, code, description, datetime) values (?, ?, ?, ?)",
                idWorker.nextId(), "UTC", "UTC", dateUtc);

    }

}
