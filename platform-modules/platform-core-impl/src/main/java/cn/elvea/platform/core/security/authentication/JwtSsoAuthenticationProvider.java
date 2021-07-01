package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.CustomAuthenticationProvider;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtSsoAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtSsoAuthenticationProvider extends CustomAuthenticationProvider {

    public JwtSsoAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtRefreshTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
