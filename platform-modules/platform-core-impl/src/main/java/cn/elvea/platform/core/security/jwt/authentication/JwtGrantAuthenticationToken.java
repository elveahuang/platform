package cn.elvea.platform.core.security.jwt.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * JwtGrantAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class JwtGrantAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String grantType;

    public JwtGrantAuthenticationToken(String grantType) {
        super(Collections.emptyList());
        Assert.notNull(grantType, "grantType cannot be null");
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }

}
