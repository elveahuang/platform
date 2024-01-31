package cn.elvea.platform.system.commons.api;

import cn.elvea.platform.commons.core.captcha.Captcha;
import cn.elvea.platform.commons.core.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaApi {

    Captcha generate(CaptchaRequest request) throws Exception;

    boolean check(CaptchaCheckRequest request);

}
