package cn.elvea.platform.security.web;

import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;
import cn.elvea.platform.commons.core.exception.InvalidCaptchaException;
import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.api.UserSessionApi;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final UserSessionApi userSessionApi;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) {
        log.error("CustomAuthenticationSuccessHandler.onAuthenticationFailure...");

        // 保存用户登录失败记录
        try {
            String clientId = "";
            String clientName = "";
            String username = request.getParameter(OAuth2ParameterNames.USERNAME);
            if (StringUtils.isNotEmpty(username)) {
                UserSessionDto userSession = UserSessionDto.builder()
                        .userId(0L)
                        .username(username)
                        .success(Boolean.TRUE)
                        .ua(ServletUtils.getUserAgent())
                        .host(ServletUtils.getHost(request))
                        .clientId(clientId)
                        .clientName(clientName)
                        .build();
                this.userSessionApi.saveUserSession(userSession);
            }
        } catch (Exception e) {
            log.error("Failed to save UserSession.", e);
        }

        SecurityContextHolder.clearContext();

        if (ae instanceof InvalidCaptchaException) {
            ServletUtils.renderJson(response, R.fail(ResponseCodeEnum.INVALID_CAPTCHA));
        } else {
            ServletUtils.renderJson(response, R.error(ResponseCodeEnum.ERROR));
        }
    }

}
