package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.core.security.jwt.JwtAuthenticationException;
import cn.elvea.platform.core.security.service.SecurityUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JwtCaptchaAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class JwtCaptchaAuthenticationProvider extends JwtAuthenticationProvider {

    private final SecurityUserDetailsService userDetailsService;

    public JwtCaptchaAuthenticationProvider(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtCaptchaAuthenticationToken token = (JwtCaptchaAuthenticationToken) authentication;
        UserDetails user = this.userDetailsService.loadUserByUsername(token.getPrincipal().toString());
        additionalAuthenticationChecks(user, token);
        return new JwtAccessTokenAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private void additionalAuthenticationChecks(UserDetails user, JwtCaptchaAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCaptcha() == null) {
            throw new JwtAuthenticationException("验证码不能为空");
        }
        if (!authentication.getCaptcha().equalsIgnoreCase("888888")) {
            throw new JwtAuthenticationException("验证码不匹配");
        }
    }

}
