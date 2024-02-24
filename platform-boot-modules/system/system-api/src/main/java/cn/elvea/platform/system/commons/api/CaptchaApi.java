package cn.elvea.platform.system.commons.api;

import cn.elvea.platform.commons.extensions.captcha.Captcha;
import cn.elvea.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaApi {

    Captcha generate(CaptchaRequest request) throws Exception;

    boolean check(CaptchaCheckRequest request);

}
