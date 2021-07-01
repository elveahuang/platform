package cn.elvea.platform.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ExpiredAccessTokenException
 *
 * @author elvea
 * @since 0.0.1
 */
public class ExpiredAccessTokenException extends AuthenticationException {

    public ExpiredAccessTokenException(String message) {
        super(message);
    }

}
