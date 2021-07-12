package cn.elvea.platform.core.socket.interceptor;

import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.core.security.SecurityUtils;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import cn.elvea.platform.core.socket.WebSocketUserSession;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static cn.elvea.platform.core.socket.WebSocketConstants.WEB_SOCKET_USER_SESSION_REQUEST_KEY;

/**
 * 握手拦截器，用于权限检查
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtSecurityService jwtSecurityService;

    public WebSocketHandshakeInterceptor(JwtSecurityService jwtSecurityService) {
        this.jwtSecurityService = jwtSecurityService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        log.debug("web socket before handshake start...");
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

        String servletSessionId = servletRequest.getSession().getId();
        String host = ServletUtils.getHost(servletRequest);

        log.debug("web socket before handshake servletSessionId - {}.", servletSessionId);
        try {
            String accessToken = SecurityUtils.getAccessTokenCode(servletRequest);
            if (!StringUtils.hasLength(accessToken)) {
                log.debug("web socket before handshake failed. servletSessionId - {}. host - {}.", servletSessionId, host);
                return false;
            }

            JWTClaimsSet claimsSet = this.jwtSecurityService.parseAccessToken(accessToken);
            Long uid = claimsSet.getLongClaim(JwtUtils.JWT_KEY_UID);
            String username = claimsSet.getStringClaim(JwtUtils.JWT_KEY_ACCOUNT);
            String userSessionId = claimsSet.getStringClaim(JwtUtils.JWT_KEY_SESSION_ID);

            WebSocketUserSession webSocketUserSession = new WebSocketUserSession();
            webSocketUserSession.setUid(uid);
            webSocketUserSession.setUsername(username);
            webSocketUserSession.setUserSessionId(userSessionId);
            webSocketUserSession.setServletSessionId(servletSessionId);

            attributes.put(WEB_SOCKET_USER_SESSION_REQUEST_KEY, webSocketUserSession);

            log.debug("web socket before handshake success. servletSessionId - {}. host - {}. username - {}. userSessionId - {}.", servletSessionId, host, username, userSessionId);
            return true;
        } catch (Exception e) {
            log.error("web socket before handshake failed. servletSessionId - {}. host - {}.", servletSessionId, host, e);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.debug("web socket after handshake start.");
    }

}
