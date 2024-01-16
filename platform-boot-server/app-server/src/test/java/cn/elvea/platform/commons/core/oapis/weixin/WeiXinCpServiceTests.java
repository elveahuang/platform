package cn.elvea.platform.commons.core.oapis.weixin;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.oapis.weixin.service.WeiXinCpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class WeiXinCpServiceTests extends BaseTests {

    @Autowired(required = false)
    WeiXinCpService weiXinCpService;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(weiXinCpService);
        String accessToken = weiXinCpService.getService().getAccessToken();
        Assertions.assertNotNull(accessToken);
        String jsapiTicket = weiXinCpService.getService().getJsapiTicket();
        Assertions.assertNotNull(jsapiTicket);
    }

}
