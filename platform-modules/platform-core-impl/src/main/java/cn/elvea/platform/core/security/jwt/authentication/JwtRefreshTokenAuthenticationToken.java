package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.core.security.jwt.JwtSecurityConstants;
import org.springframework.util.Assert;

/**
 * JwtRefreshTokenAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtRefreshTokenAuthenticationToken extends JwtGrantAuthenticationToken {

    private final String refreshToken;

    public JwtRefreshTokenAuthenticationToken(String refreshToken) {
        super(JwtSecurityConstants.REFRESH_TOKEN);
        Assert.hasText(refreshToken, "refreshToken cannot be empty");
        this.refreshToken = refreshToken;
    }

    @Override
    public Object getPrincipal() {
        return this.refreshToken;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
