package cn.elvea.platform.core.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * InvalidGrantTypeException
 *
 * @author elvea
 * @since 0.0.1
 */
public class InvalidGrantTypeException extends AuthenticationException {

    public InvalidGrantTypeException() {
        super("Invalid grant type.");
    }

    public InvalidGrantTypeException(String message) {
        super(message);
    }

}
