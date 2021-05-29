package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.core.security.jwt.JwtAuthenticationException;
import cn.elvea.platform.core.security.service.SecurityUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * JwtPasswordAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class JwtPasswordAuthenticationProvider extends JwtAuthenticationProvider {

    private final SecurityUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public JwtPasswordAuthenticationProvider(SecurityUserDetailsService userDetailsService,
                                             PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtPasswordAuthenticationToken token = (JwtPasswordAuthenticationToken) authentication;
        UserDetails user = this.userDetailsService.loadUserByUsername(token.getPrincipal().toString());
        additionalAuthenticationChecks(user, token);
        return new JwtAccessTokenAuthenticationToken(user, user.getAuthorities());
    }

    private void additionalAuthenticationChecks(UserDetails user, JwtPasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new JwtAuthenticationException("密码不能为空");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, user.getPassword())) {
            throw new JwtAuthenticationException("用户或者密码不正确");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
