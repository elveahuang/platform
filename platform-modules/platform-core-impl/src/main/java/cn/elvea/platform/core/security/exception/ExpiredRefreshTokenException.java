package cn.elvea.platform.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ExpiredRefreshTokenException
 *
 * @author elvea
 * @since 0.0.1
 */
public class ExpiredRefreshTokenException extends AuthenticationException {

    public ExpiredRefreshTokenException(String message) {
        super(message);
    }

}
