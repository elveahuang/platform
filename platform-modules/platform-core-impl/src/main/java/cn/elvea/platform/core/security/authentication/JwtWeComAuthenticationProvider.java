package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.CustomAuthenticationProvider;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtWeComAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtWeComAuthenticationProvider extends CustomAuthenticationProvider {

    public JwtWeComAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtWeComAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
