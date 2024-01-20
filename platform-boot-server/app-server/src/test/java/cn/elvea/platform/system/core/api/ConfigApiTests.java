package cn.elvea.platform.system.core.api;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.system.commons.constants.SystemConfigConstants;
import cn.elvea.platform.system.config.api.ConfigApi;
import cn.elvea.platform.system.config.model.dto.ConfigDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class ConfigApiTests extends BaseTests {

    @Autowired
    ConfigApi configApi;

    @Test
    public void baseTest() {
        ConfigDto appTitle = this.configApi.get(SystemConfigConstants.APP_TITLE);
        ConfigDto appCopyright = this.configApi.get(SystemConfigConstants.APP_COPYRIGHT);
        Assertions.assertNotNull(appTitle);
        Assertions.assertNotNull(appCopyright);
    }

}
