package cn.elvea.platform.commons.core.captcha.provider;

import cn.elvea.platform.commons.core.captcha.Captcha;
import cn.elvea.platform.commons.core.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaProvider {

    Captcha generate(CaptchaRequest request) throws Exception;

}
