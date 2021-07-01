package cn.elvea.platform.core.security;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.web.Response;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未授权处理
 *
 * @author elvea
 * @since 0.0.1
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ServletUtils.renderJson(servletResponse, Response.error());
    }

}
