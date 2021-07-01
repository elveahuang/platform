package cn.elvea.platform.core.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * JwtAccessTokenAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final JwtAuthenticationResponse response;

    public JwtAuthenticationToken(Object principal, Object credentials,
                                  Collection<? extends GrantedAuthority> authorities,
                                  JwtAuthenticationResponse response) {
        super(principal, credentials, authorities);
        this.response = response;
    }

    public JwtAuthenticationResponse getResponse() {
        return response;
    }

}
