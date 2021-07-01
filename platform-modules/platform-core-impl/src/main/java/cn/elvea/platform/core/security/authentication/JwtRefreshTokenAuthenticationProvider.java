package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.CustomAuthenticationProvider;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import cn.elvea.platform.core.security.SecurityUser;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationResponse;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationToken;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * JwtRefreshTokenAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtRefreshTokenAuthenticationProvider extends CustomAuthenticationProvider {

    private final JwtSecurityService jwtSecurityService;

    public JwtRefreshTokenAuthenticationProvider(CustomUserDetailsService userDetailsService, JwtSecurityService jwtSecurityService) {
        super(userDetailsService);
        this.jwtSecurityService = jwtSecurityService;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtRefreshTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    protected JwtAuthenticationToken createSuccessAuthentication(Object principal,
                                                                 Authentication authentication,
                                                                 UserDetails userDetails) {
        try {

            JwtRefreshTokenAuthenticationToken token = (JwtRefreshTokenAuthenticationToken) authentication;

            SecurityUser user = (SecurityUser) principal;

            String accessToken = this.jwtSecurityService.createAccessToken(token.getSessionId(), user);
            this.jwtSecurityService.parseAccessToken(accessToken);
            JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
                    .access_token(accessToken)
                    .refresh_token(token.getRefreshToken())
                    .build();
            JwtAuthenticationToken result = new JwtAuthenticationToken(principal, "", userDetails.getAuthorities(), response);
            result.setDetails(authentication.getDetails());
            return result;
        } catch (Exception e) {
            log.error("Failed to createSuccessAuthentication.", e);
            throw new InvalidRequestException();
        }
    }

}
