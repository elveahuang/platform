package cn.elvea.platform.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * InvalidCaptchaException
 *
 * @author elvea
 * @since 0.0.1
 */
public class InvalidCaptchaException extends AuthenticationException {

    public InvalidCaptchaException(String message) {
        super(message);
    }

}
