package cn.elvea.platform.core.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * JwtAuthenticationException
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
