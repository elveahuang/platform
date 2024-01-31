package cn.elvea.platform.commons.core.time;

import cn.elvea.platform.commons.core.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import static cn.elvea.platform.commons.core.utils.ObjectUtils.nvl;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class TimeZoneManager {

    private static final TimeZoneResolver resolver = new DefaultTimeZoneResolver();

    public static TimeZoneResolver getResolver() {
        return nvl(SpringUtils.getBean(TimeZoneResolver.class), resolver);
    }

}
