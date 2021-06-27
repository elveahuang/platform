package cn.elvea.platform.xapi.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * XApiResponse
 *
 * @author elvea
 */
@Setter
@Getter
public class XApiResponse<E> implements Serializable {
    public final static int SUCCESS = 1;
    public final static int ERROR = 0;

    /**
     * 状态编码
     */
    private int code;
    /**
     * 信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;

    public static XApiResponse<?> success() {
        return new XApiResponse<>(SUCCESS);
    }

    public static <T> XApiResponse<T> success(T data) {
        XApiResponse<T> result = new XApiResponse<>(SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> XApiResponse<T> error(String message) {
        XApiResponse<T> result = new XApiResponse<>(ERROR);
        result.setMessage(message);
        return result;
    }

    private XApiResponse(int code) {
        this.code = code;
    }

}
