package cn.elvea.platform.security.web;

import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;
import cn.elvea.platform.commons.core.exception.InvalidCaptchaException;
import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.commons.core.web.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.error("onAuthenticationFailure", e);

        SecurityContextHolder.clearContext();

        if (e instanceof InvalidCaptchaException) {
            ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.INVALID_CAPTCHA));
        } else {
            ServletUtils.renderJson(response, R.error(ResponseCodeEnum.ERROR));
        }
    }

}
