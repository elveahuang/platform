package cn.elvea.platform.system.commons.api.impl;

import cn.elvea.platform.system.commons.api.CoreApi;
import cn.elvea.platform.system.commons.model.vo.InitializeVo;
import cn.elvea.platform.system.config.api.ConfigApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.system.commons.constants.SystemConfigConstants.LOGIN_CAPTCHA_ENABLED;

/**
 * @author elvea
 * @since 24.1.0
 */
@Service
@AllArgsConstructor
public class CoreApiImpl implements CoreApi {

    private final ConfigApi configApi;

    @Override
    public InitializeVo initialize() {
        return InitializeVo.builder()
                .loginCaptchaEnabled(this.configApi.getBoolean(LOGIN_CAPTCHA_ENABLED))
                .build();
    }

}
