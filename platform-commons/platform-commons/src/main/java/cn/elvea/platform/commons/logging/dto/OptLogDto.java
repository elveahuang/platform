package cn.elvea.platform.commons.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * OptLogDto
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptLogDto implements Serializable {
    /**
     * 类名
     */
    private String className;
    /**
     * 函数名
     */
    private String methodName;
    /**
     * 操作IP
     */
    private String requestIp;
    /**
     * 请求地址
     */
    private String requestUri;
    /**
     * 请求类型
     */
    private String httpMethod;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 注解参数
     */
    private String annotationParams;
    /**
     * 日志的开始时间
     */
    private LocalDateTime startTime;
    /**
     * 执行时长
     */
    private Long execTime;
    /**
     * 错误日志详细信息
     */
    private String exception;
}
