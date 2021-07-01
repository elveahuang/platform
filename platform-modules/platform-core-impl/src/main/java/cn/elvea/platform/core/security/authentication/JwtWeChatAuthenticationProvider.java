package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.CustomAuthenticationProvider;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtWeChatAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtWeChatAuthenticationProvider extends CustomAuthenticationProvider {

    public JwtWeChatAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        super(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtWeChatAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
