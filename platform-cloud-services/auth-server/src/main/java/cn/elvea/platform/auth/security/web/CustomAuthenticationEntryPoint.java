package cn.elvea.platform.auth.security.web;

import cn.elvea.platform.commons.enums.ResponseCodeEnum;
import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.web.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("CustomAuthenticationEntryPoint.", e);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.UNAUTHORIZED));
    }

}
