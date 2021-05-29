package cn.elvea.platform.commons.utils;

import cn.elvea.platform.base.constants.DateTimeFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static cn.elvea.platform.base.constants.DateTimeConstants.TIME_ZONE_CHINA;
import static cn.elvea.platform.base.constants.DateTimeConstants.TIME_ZONE_UTC;

/**
 * DateTimeUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateUtilsTests {

    @Test
    public void formatTests() {
        String text = "2021-02-03 09:08:07.654";
        Date date = DateUtils.parse(text, DateTimeFormat.Pattern.FULL_DATE_TIME);
        Assertions.assertEquals("09:08:07", DateUtils.format(date, DateTimeFormat.Pattern.TIME));
        Assertions.assertEquals("09:08", DateUtils.format(date, DateTimeFormat.Pattern.SIMPLE_TIME));
        Assertions.assertEquals("2021-02-03", DateUtils.format(date, DateTimeFormat.Pattern.DATE));
        Assertions.assertEquals("2021-02-03 09:08:07", DateUtils.format(date, DateTimeFormat.Pattern.DATE_TIME));
        Assertions.assertEquals("2021-02-03 09:08:07", DateUtils.format(date, DateTimeFormat.Pattern.DATE_TIME, TIME_ZONE_CHINA));
        Assertions.assertEquals("2021-02-03 01:08:07", DateUtils.format(date, DateTimeFormat.Pattern.DATE_TIME, TIME_ZONE_UTC));
        Assertions.assertEquals("2021-02-03 09:08:07.654", DateUtils.format(date, DateTimeFormat.Pattern.FULL_DATE_TIME));
        Assertions.assertEquals("2021-02-03 09:08:07.654", DateUtils.format(date, DateTimeFormat.Pattern.FULL_DATE_TIME, TIME_ZONE_CHINA));
        Assertions.assertEquals("2021-02-03 01:08:07.654", DateUtils.format(date, DateTimeFormat.Pattern.FULL_DATE_TIME, TIME_ZONE_UTC));
        Assertions.assertEquals("2021-02-03 09:08", DateUtils.format(date, DateTimeFormat.Pattern.SIMPLE_DATE_TIME));
        Assertions.assertEquals("2021-02-03 09:08", DateUtils.format(date, DateTimeFormat.Pattern.SIMPLE_DATE_TIME, TIME_ZONE_CHINA));
        Assertions.assertEquals("2021-02-03 01:08", DateUtils.format(date, DateTimeFormat.Pattern.SIMPLE_DATE_TIME, TIME_ZONE_UTC));
    }

    @Test
    public void parseTests() {
    }

    @Test
    public void test() {
    }

}
