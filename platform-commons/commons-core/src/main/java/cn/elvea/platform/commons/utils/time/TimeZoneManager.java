package cn.elvea.platform.commons.utils.time;

import cn.elvea.platform.commons.utils.SpringUtils;
import cn.elvea.platform.commons.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class TimeZoneManager {

    private static final TimeZoneResolver resolver = new DefaultTimeZoneResolver();

    public static TimeZoneResolver getResolver() {
        return ObjectUtils.nvl(SpringUtils.getBean(TimeZoneResolver.class), resolver);
    }

}
