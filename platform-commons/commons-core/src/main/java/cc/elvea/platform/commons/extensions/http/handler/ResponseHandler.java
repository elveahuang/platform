package cc.elvea.platform.commons.extensions.http.handler;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ResponseHandler<T> {
    void handle(T t) throws Exception;
}
