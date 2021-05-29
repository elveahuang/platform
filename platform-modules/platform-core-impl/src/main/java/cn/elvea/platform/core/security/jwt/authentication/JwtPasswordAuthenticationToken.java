package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.core.security.jwt.JwtSecurityConstants;
import org.springframework.util.Assert;

/**
 * JwtPasswordAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtPasswordAuthenticationToken extends JwtGrantAuthenticationToken {

    private final Object principal;

    private final Object credentials;

    public JwtPasswordAuthenticationToken(String principal, String credentials) {
        super(JwtSecurityConstants.PASSWORD);
        Assert.hasText(principal, "principal cannot be empty");
        Assert.hasText(credentials, "credentials cannot be empty");
        this.principal = principal;
        this.credentials = credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public Object getCredentials() {
        return credentials;
    }

}
