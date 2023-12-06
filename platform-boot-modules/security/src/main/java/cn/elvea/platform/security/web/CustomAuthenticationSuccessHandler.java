package cn.elvea.platform.security.web;

import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.elvea.platform.system.core.api.UserSessionApi;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author elvea
 * @since 2.0.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserSessionApi userSessionApi;

    private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomAuthenticationSuccessHandler.onAuthenticationSuccess...");

        // 保存用户成功登录记录
        try {
            String clientId = "";
            String clientName = "";
            if (authentication instanceof OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken) {
                clientId = accessTokenAuthenticationToken.getRegisteredClient().getClientId();
                clientName = accessTokenAuthenticationToken.getRegisteredClient().getClientName();
            }

            UserSessionDto userSession = UserSessionDto.builder()
                    .userId(SecurityUtils.getUid(authentication))
                    .username(SecurityUtils.getUsername(authentication))
                    .success(Boolean.TRUE)
                    .ua(ServletUtils.getUserAgent())
                    .host(ServletUtils.getHost(request))
                    .clientId(clientId)
                    .clientName(clientName)
                    .build();
            this.userSessionApi.saveUserSession(userSession);
        } catch (Exception e) {
            log.error("Failed to save UserSession.", e);
        }

        // 返回访问凭证
        try {
            sendAccessTokenResponse(request, response, authentication);
            log.info("CustomAuthenticationSuccessHandler.onAuthenticationSuccess done.");
        } catch (IOException e) {
            log.error("CustomAuthenticationSuccessHandler.onAuthenticationSuccess failed.", e);
        }
    }

    private void sendAccessTokenResponse(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;

        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse
                .withToken(accessToken.getTokenValue())
                .tokenType(accessToken.getTokenType())
                .scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }
        if (!CollectionUtils.isEmpty(additionalParameters)) {
            builder.additionalParameters(additionalParameters);
        }
        OAuth2AccessTokenResponse accessTokenResponse = builder.build();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        this.accessTokenHttpResponseConverter.write(accessTokenResponse, null, httpResponse);
    }

}
