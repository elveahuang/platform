package cn.elvea.platform.core.security.filter;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.web.Response;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationConverter;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.elvea.platform.core.security.SecurityConstants.API_AUTH_TOKEN_PATH;

/**
 * 认证过滤器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static AntPathRequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher(API_AUTH_TOKEN_PATH, "POST");

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) {
        super(REQUEST_MATCHER, authenticationManager);
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = this.jwtAuthenticationConverter.convert(request);
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        try {
            if (authentication instanceof JwtAuthenticationToken) {
                JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
                ServletUtils.renderJson(servletResponse, Response.success(token.getResponse()));
            }
        } catch (Exception e) {
            log.error("Failed to create token response.", e);
            ServletUtils.renderJson(servletResponse, Response.error("System Exception"));
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                              AuthenticationException exception) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        Response<?> response;
        if (exception instanceof InvalidRequestException) {
            response = Response.error("JwtInvalidRequestException.");
        } else {
            response = Response.error("Invalid Request.");
        }
        ServletUtils.renderJson(servletResponse, response);
    }

}
