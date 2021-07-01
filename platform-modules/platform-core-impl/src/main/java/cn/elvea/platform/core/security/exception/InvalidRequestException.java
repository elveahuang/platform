package cn.elvea.platform.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * InvalidRequestException
 *
 * @author elvea
 * @since 0.0.1
 */
public class InvalidRequestException extends AuthenticationException {

    public InvalidRequestException() {
        super("Invalid Request.");
    }

    public InvalidRequestException(String message) {
        super(message);
    }

}
