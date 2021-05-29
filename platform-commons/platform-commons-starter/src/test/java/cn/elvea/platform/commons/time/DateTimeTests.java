package cn.elvea.platform.commons.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.TimeZone;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;

/**
 * 日期相关单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateTimeTests {

    @Test
    public void baseTests() {
        System.out.println(TimeZone.getTimeZone(ZoneId.systemDefault()));
        System.out.println(TimeZone.getDefault());
        System.out.println(TimeZone.getTimeZone("CTT"));
        System.out.println(TimeZone.getTimeZone("UTC"));
        System.out.println(LocalDateTime.now());
    }

    @Test
    public void test() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(DAY_OF_MONTH, 1)
                .toFormatter();
        LocalDate date = LocalDate.parse("2021-01", formatter);
        System.out.println(date);
    }

}
