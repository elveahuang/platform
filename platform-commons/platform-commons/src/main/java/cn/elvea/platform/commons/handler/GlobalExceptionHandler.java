package cn.elvea.platform.commons.handler;

import cn.elvea.platform.commons.exception.ServiceException;
import cn.elvea.platform.commons.exception.SystemException;
import cn.elvea.platform.commons.web.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Response<?> systemExceptionHandler(HttpServletResponse response, SystemException e) {
        return Response.error();
    }

    @ExceptionHandler(ServiceException.class)
    public Response<?> serviceExceptionHandler(HttpServletResponse response, ServiceException e) {
        return Response.error();
    }

}
