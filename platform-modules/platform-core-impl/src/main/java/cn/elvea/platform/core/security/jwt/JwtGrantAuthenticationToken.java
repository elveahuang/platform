package cn.elvea.platform.core.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * JwtGrantAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class JwtGrantAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String grantType;

    public JwtGrantAuthenticationToken(String grantType) {
        super("", "");
        Assert.notNull(grantType, "grantType cannot be null");
        this.grantType = grantType;
    }

    public JwtGrantAuthenticationToken(String principal, String grantType) {
        super(principal, "");
        Assert.notNull(grantType, "grantType cannot be null");
        this.grantType = grantType;
    }

    public JwtGrantAuthenticationToken(String principal, String credentials, String grantType) {
        super(principal, credentials);
        Assert.notNull(grantType, "grantType cannot be null");
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }

}
