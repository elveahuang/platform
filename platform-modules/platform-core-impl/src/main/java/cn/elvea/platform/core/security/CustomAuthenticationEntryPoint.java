package cn.elvea.platform.core.security;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.web.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomAuthenticationEntryPoint
 *
 * @author elvea
 * @since 0.0.1
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AuthenticationException authException) throws IOException, ServletException {
        ServletUtils.renderJson(servletResponse, Response.error());
    }

}
