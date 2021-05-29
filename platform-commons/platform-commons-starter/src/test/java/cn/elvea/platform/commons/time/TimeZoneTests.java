package cn.elvea.platform.commons.time;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Set;
import java.util.TimeZone;

/**
 * 时区单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class TimeZoneTests {

    @Test
    public void timeZoneTests() {
        String[] timeZones = TimeZone.getAvailableIDs();
        for (String timeZone : timeZones) {
            System.out.println(timeZone);
        }
    }

    @Test
    public void zoneIdTests() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String zoneId : zoneIds) {
            System.out.println(zoneId);
        }
    }

}
