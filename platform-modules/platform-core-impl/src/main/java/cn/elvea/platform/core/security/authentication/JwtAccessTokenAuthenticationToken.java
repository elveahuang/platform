package cn.elvea.platform.core.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

/**
 * JwtAccessTokenAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtAccessTokenAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String accessToken;

    public JwtAccessTokenAuthenticationToken(String principal, String accessToken) {
        super(principal, "");
        Assert.hasText(accessToken, "accessToken cannot be empty");
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
