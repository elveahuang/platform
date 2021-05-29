package cn.elvea.platform.commons.utils;

import cn.elvea.platform.base.constants.DateTimeConstants;
import cn.elvea.platform.base.constants.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * DateTimeUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class DateTimeUtilsTests {

    @Test
    public void formatterTests() {
        String text = "2021-01-01";
        String pattern = DateTimeFormat.DEFAULT_DATE_PATTERN;
        DateTimeFormatter formatter = DateTimeFormat.DATE_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalDate.parse(text, formatter));

        text = "2021-02-03 04:05:06";
        pattern = DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;
        formatter = DateTimeFormat.DATE_TIME_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalDateTime.parse(text, formatter));

        text = "2021-02-03 04:05";
        pattern = DateTimeFormat.DEFAULT_SIMPLE_DATE_TIME_PATTERN;
        formatter = DateTimeFormat.SIMPLE_DATE_TIME_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalDateTime.parse(text, formatter));

        text = "2021-02-03 04:05:06.789";
        pattern = DateTimeFormat.DEFAULT_FULL_DATE_TIME_PATTERN;
        formatter = DateTimeFormat.FULL_DATE_TIME_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalDateTime.parse(text, formatter));

        text = "01:02:03";
        pattern = DateTimeFormat.DEFAULT_TIME_PATTERN;
        formatter = DateTimeFormat.TIME_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalTime.parse(text, formatter));

        text = "01:02";
        pattern = DateTimeFormat.DEFAULT_SIMPLE_TIME_PATTERN;
        formatter = DateTimeFormat.SIMPLE_TIME_FORMATTER;
        log.debug("text [{}] - pattern [{}] - result - [{}]", text, pattern, LocalTime.parse(text, formatter));
    }

    @Test
    public void parseTests() {
        Date date = new Date();
        System.out.println(date);

        String text = DateUtils.format(date, DateTimeFormat.Pattern.DATE_TIME);
        LocalDateTime localDateTime1 = DateTimeUtils.parse(text, DateTimeFormat.DEFAULT_DATE_TIME_PATTERN, LocalDateTime.class);
        LocalDateTime localDateTime2 = DateTimeUtils.parse(text, DateTimeFormat.Pattern.DATE_TIME, LocalDateTime.class);
        LocalDateTime localDateTime3 = DateTimeUtils.parse(text, DateTimeFormat.DATE_TIME_FORMATTER, LocalDateTime.class);
        System.out.println(text);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);

        text = DateUtils.format(date, DateTimeFormat.Pattern.DATE);
        LocalDate localDate1 = DateTimeUtils.parse(text, DateTimeFormat.DEFAULT_DATE_PATTERN, LocalDate.class);
        LocalDate localDate2 = DateTimeUtils.parse(text, DateTimeFormat.Pattern.DATE, LocalDate.class);
        LocalDate localDate3 = DateTimeUtils.parse(text, DateTimeFormat.DATE_FORMATTER, LocalDate.class);
        System.out.println(text);
        System.out.println(localDate1);
        System.out.println(localDate2);
        System.out.println(localDate3);

        text = DateUtils.format(date, DateTimeFormat.Pattern.TIME);
        LocalTime localTime1 = DateTimeUtils.parse(text, DateTimeFormat.DEFAULT_TIME_PATTERN, LocalTime.class);
        LocalTime localTime2 = DateTimeUtils.parse(text, DateTimeFormat.Pattern.TIME, LocalTime.class);
        LocalTime localTime3 = DateTimeUtils.parse(text, DateTimeFormat.TIME_FORMATTER, LocalTime.class);
        System.out.println(text);
        System.out.println(localTime1);
        System.out.println(localTime2);
        System.out.println(localTime3);
    }

    @Test
    public void transferTests() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(DateTimeUtils.transfer(now, DateTimeConstants.ZONE_ID_CHINA));
        System.out.println(DateTimeUtils.transfer(now, DateTimeConstants.ZONE_ID_UTC));
        System.out.println(DateTimeUtils.transfer(now, DateTimeConstants.ZONE_ID_CHINA, DateTimeConstants.ZONE_ID_UTC));
        System.out.println(DateTimeUtils.transfer(now, DateTimeConstants.ZONE_ID_UTC, DateTimeConstants.ZONE_ID_CHINA));

        Date date = new Date();
        System.out.println(DateTimeUtils.toLocalDateTime(date));
        System.out.println(DateTimeUtils.toLocalDate(date));
        System.out.println(DateTimeUtils.toLocalTime(date));
    }

    @Test
    public void tests() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

}
