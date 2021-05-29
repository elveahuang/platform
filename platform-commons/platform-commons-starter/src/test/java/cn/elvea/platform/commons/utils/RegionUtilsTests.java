package cn.elvea.platform.commons.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RegionUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class RegionUtilsTests {

    @Test
    public void test() throws Exception {
        RegionUtils.McaData data = RegionUtils.fetchMcaData();
        Assertions.assertNotNull(data);
    }

}
