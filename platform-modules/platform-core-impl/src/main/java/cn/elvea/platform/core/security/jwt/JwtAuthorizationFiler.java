package cn.elvea.platform.core.security.jwt;

import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.core.security.jwt.authentication.JwtAccessTokenAuthenticationToken;
import cn.elvea.platform.core.security.service.JwtSecurityService;
import cn.elvea.platform.core.security.service.SecurityUserDetailsService;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthorizationFiler
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtAuthorizationFiler extends OncePerRequestFilter {

    private final JwtSecurityService securityService;
    private final SecurityUserDetailsService userDetailsService;

    public JwtAuthorizationFiler(JwtSecurityService securityService, SecurityUserDetailsService userDetailsService) {
        this.securityService = securityService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain) throws ServletException, IOException {
        try {
            String accessToken = JwtUtils.getAccessToken(request);

            if (StringUtils.hasText(accessToken)) {
                JWTClaimsSet claimsSet = this.securityService.parseAccessToken(accessToken);
                String username = claimsSet.getStringClaim(JwtUtils.JWT_KEY_ACCOUNT);
                UserDetails user = this.userDetailsService.loadUserByUsername(username);
                Authentication authentication = new JwtAccessTokenAuthenticationToken(user, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SecurityContextHolder.clearContext();
        chain.doFilter(request, response);
    }

}
