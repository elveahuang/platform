package cn.elvea.platform.commons.extensions.captcha.provider;

import cn.elvea.platform.commons.extensions.captcha.Captcha;
import cn.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaProvider {

    Captcha generate(CaptchaRequest request) throws Exception;

}
