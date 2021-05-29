package cn.elvea.platform.core.security.jwt;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.core.security.jwt.authentication.JwtAccessTokenAuthenticationToken;
import cn.elvea.platform.core.security.jwt.authentication.JwtCaptchaAuthenticationConverter;
import cn.elvea.platform.core.security.jwt.authentication.JwtPasswordAuthenticationConverter;
import cn.elvea.platform.core.security.jwt.authentication.JwtRefreshTokenAuthenticationConverter;
import cn.elvea.platform.core.security.service.JwtSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.elvea.platform.core.security.jwt.JwtSecurityConstants.AUTH_TOKEN_PATH;

/**
 * JwtAuthenticationFilter
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher(AUTH_TOKEN_PATH, "POST");

    private final AuthenticationManager authenticationManager;

    private final AuthenticationConverter authenticationConverter;

    private final JwtSecurityService securityService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtSecurityService securityService) {
        this.securityService = securityService;
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");

        this.authenticationManager = authenticationManager;

        List<AuthenticationConverter> converters = new ArrayList<>();
        converters.add(new JwtCaptchaAuthenticationConverter());
        converters.add(new JwtPasswordAuthenticationConverter());
        converters.add(new JwtRefreshTokenAuthenticationConverter());
        this.authenticationConverter = new JwtAuthenticationConverter(converters);
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        if (!REQUEST_MATCHER.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
            if (!StringUtils.hasText(grantType)) {
                log.error("Invalid Request.");
            }

            Authentication authentication = this.authenticationConverter.convert(request);
            if (authentication == null) {
                log.error("Unsupported Grant Type.");
            }

            JwtAccessTokenAuthenticationToken authenticationToken =
                    (JwtAccessTokenAuthenticationToken) this.authenticationManager.authenticate(authentication);
            sendAccessTokenResponse(response, authenticationToken);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
        }
    }

    private void sendAccessTokenResponse(HttpServletResponse response, JwtAccessTokenAuthenticationToken authentication) throws Exception {
        JwtAccessTokenResponse accessTokenResponse = JwtAccessTokenResponse.builder()
                .access_token(this.securityService.createAccessToken(authentication))
                .refresh_token(this.securityService.createRefreshToken(authentication))
                .build();
        ServletUtils.renderJson(response, accessTokenResponse);
    }

}
