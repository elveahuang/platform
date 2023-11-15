package cn.elvea.platform.commons.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author elvea
 * @since 0.0.1
 */
public class InvalidCaptchaException extends AuthenticationException {

    public InvalidCaptchaException(String msg) {
        super(msg);
    }

    public InvalidCaptchaException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
