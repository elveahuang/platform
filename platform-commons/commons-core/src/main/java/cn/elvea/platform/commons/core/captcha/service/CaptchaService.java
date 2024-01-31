package cn.elvea.platform.commons.core.captcha.service;

import cn.elvea.platform.commons.core.captcha.provider.CaptchaProvider;
import cn.elvea.platform.commons.core.captcha.Captcha;
import cn.elvea.platform.commons.core.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.captcha.request.CaptchaRequest;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaService {

    Captcha generate(CaptchaRequest request) throws Exception;

    /**
     * 检查验证码
     */
    boolean check(CaptchaCheckRequest request);

    /**
     * 检查验证码，不管结果是否成功，检查后都将删除验证码
     */
    boolean validate(CaptchaCheckRequest request);

    CaptchaProvider getCaptchaProvider(CaptchaRequest request);

}
