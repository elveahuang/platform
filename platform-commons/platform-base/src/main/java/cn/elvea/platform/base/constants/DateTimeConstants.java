package cn.elvea.platform.base.constants;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * DateTimeConstants
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateTimeConstants {

    public final static ZoneId ZONE_ID_DEFAULT;
    public final static ZoneId ZONE_ID_UTC;
    public final static ZoneId ZONE_ID_CHINA;

    public final static TimeZone TIME_ZONE_DEFAULT;
    public final static TimeZone TIME_ZONE_UTC;
    public final static TimeZone TIME_ZONE_CHINA;

    static {
        ZONE_ID_DEFAULT = ZoneId.systemDefault();
        ZONE_ID_UTC = ZoneId.of("UTC");
        ZONE_ID_CHINA = ZoneId.of("+08:00");

        TIME_ZONE_DEFAULT = TimeZone.getTimeZone(ZONE_ID_DEFAULT);
        TIME_ZONE_UTC = TimeZone.getTimeZone(ZONE_ID_UTC);
        TIME_ZONE_CHINA = TimeZone.getTimeZone(ZONE_ID_CHINA);
    }

}
