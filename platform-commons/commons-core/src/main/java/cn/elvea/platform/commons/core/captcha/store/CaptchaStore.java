package cn.elvea.platform.commons.core.captcha.store;

import cn.elvea.platform.commons.core.captcha.Captcha;

import java.time.Duration;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaStore {

    Captcha get(String key);

    void set(String key, Captcha value, Duration duration);

    void remove(String key);

}
