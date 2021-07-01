package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.jwt.JwtGrantAuthenticationToken;
import org.springframework.util.Assert;

/**
 * JwtRefreshTokenAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtRefreshTokenAuthenticationToken extends JwtGrantAuthenticationToken {

    private final String refreshToken;
    private final String sessionId;

    public JwtRefreshTokenAuthenticationToken(String principal, String refreshToken, String sessionId) {
        super(principal, "", SecurityGrantTypeEnum.CAPTCHA.getValue());
        Assert.hasText(refreshToken, "refreshToken refreshToken be empty");
        Assert.hasText(sessionId, "sessionId refreshToken be empty");
        this.refreshToken = refreshToken;
        this.sessionId = sessionId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getSessionId() {
        return sessionId;
    }

}
