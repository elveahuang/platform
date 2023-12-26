package cn.elvea.platform.commons.core.extensions.sensitive;

import cn.elvea.platform.commons.core.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;

import static cn.elvea.platform.commons.core.utils.ObjectUtils.nvl;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class SensitiveManager {

    private static final SensitiveService service = new DefaultSensitiveService();

    public static SensitiveService getService() {
        return nvl(SpringUtils.getBean(SensitiveService.class), service);
    }

}
