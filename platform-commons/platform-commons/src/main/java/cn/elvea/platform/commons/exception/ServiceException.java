package cn.elvea.platform.commons.exception;

/**
 * 服务异常
 *
 * @author elvea
 * @since 0.0.1
 */
@SuppressWarnings("unused")
public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
