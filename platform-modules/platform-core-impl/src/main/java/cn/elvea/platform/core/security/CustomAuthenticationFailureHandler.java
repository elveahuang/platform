package cn.elvea.platform.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationFailureHandler
 *
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("onAuthenticationFailure", e);
    }

}
