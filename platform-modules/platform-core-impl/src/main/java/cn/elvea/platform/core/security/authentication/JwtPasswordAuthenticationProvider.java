package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.UuidUtils;
import cn.elvea.platform.core.security.CustomAuthenticationProvider;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import cn.elvea.platform.core.security.SecurityUser;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationResponse;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationToken;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * JwtPasswordAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtPasswordAuthenticationProvider extends CustomAuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final JwtSecurityService jwtSecurityService;

    public JwtPasswordAuthenticationProvider(CustomUserDetailsService userDetailsService,
                                             PasswordEncoder passwordEncoder, JwtSecurityService jwtSecurityService) {
        super(userDetailsService);

        this.passwordEncoder = passwordEncoder;
        this.jwtSecurityService = jwtSecurityService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected JwtAuthenticationToken createSuccessAuthentication(Object principal,
                                                                 Authentication authentication,
                                                                 UserDetails userDetails) {
        try {
            SecurityUser user = (SecurityUser) principal;

            String uuid = UuidUtils.uuid();
            String accessToken = this.jwtSecurityService.createAccessToken(uuid, user);
            String refreshToken = this.jwtSecurityService.createRefreshToken(uuid, user);
            this.jwtSecurityService.parseAccessToken(accessToken);
            JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                    .access_token(accessToken)
                    .refresh_token(refreshToken)
                    .build();
            JwtAuthenticationToken result = new JwtAuthenticationToken(principal, "", userDetails.getAuthorities(), response);
            result.setDetails(authentication.getDetails());
            return result;
        } catch (Exception e) {
            log.error("Failed to createSuccessAuthentication.", e);
            throw new InvalidRequestException();
        }
    }

    private void additionalAuthenticationChecks(UserDetails user, JwtPasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("密码不能为空");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, user.getPassword())) {
            throw new BadCredentialsException("用户或者密码不正确");
        }
    }

}
