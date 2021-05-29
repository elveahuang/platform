package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationException;
import cn.elvea.platform.core.security.service.JwtSecurityService;
import cn.elvea.platform.core.security.service.SecurityUserDetailsService;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JwtRefreshTokenAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class JwtRefreshTokenAuthenticationProvider extends JwtAuthenticationProvider {

    private final SecurityUserDetailsService userDetailsService;

    private final JwtSecurityService securityService;

    public JwtRefreshTokenAuthenticationProvider(SecurityUserDetailsService userDetailsService,
                                                 JwtSecurityService securityService) {
        this.userDetailsService = userDetailsService;
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtRefreshTokenAuthenticationToken token = (JwtRefreshTokenAuthenticationToken) authentication;
        try {
            JWTClaimsSet claimsSet = this.securityService.parseRefreshToken(token.getRefreshToken());
            String username = claimsSet.getStringClaim(JwtUtils.JWT_KEY_ACCOUNT);
            UserDetails user = this.userDetailsService.loadUserByUsername(username);
            additionalAuthenticationChecks(user, token);
            return new JwtAccessTokenAuthenticationToken(user, user.getAuthorities());
        } catch (Exception e) {
            throw new JwtAuthenticationException("");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtRefreshTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void additionalAuthenticationChecks(UserDetails user, JwtRefreshTokenAuthenticationToken authentication) throws AuthenticationException {
    }

}
