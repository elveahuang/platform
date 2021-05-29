package cn.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * IpUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class IpUtilsTests {

    @Test
    public void test() throws Exception {
        String ipAddress = IpUtils.getLocalIpAddress();
        Assertions.assertNotNull(ipAddress);
    }

}
