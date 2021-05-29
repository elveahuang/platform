package cn.elvea.platform.commons.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Response
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class Response<E> implements Serializable {

    private final static int ERROR = 0;

    private final static int SUCCESS = 1;

    /**
     * 响应编号
     */
    private int code;
    /**
     * 响应信息
     */
    private String message = "success";
    /**
     * 响应数据
     */
    private E data;

    public static <T> Response<T> success() {
        return new Response<>(SUCCESS);
    }

    public static <T> Response<T> success(T data) {
        Response<T> result = new Response<>(SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error() {
        return new Response<>(ERROR);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>(ERROR, message);
    }

    private Response(int code) {
        this.code = code;
    }

    private Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
