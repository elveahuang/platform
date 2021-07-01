package cn.elvea.platform.core.security.filter;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.web.Response;
import cn.elvea.platform.core.security.SecurityUtils;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.elvea.platform.core.security.SecurityConstants.API_OPEN_REQUEST_PATH;
import static cn.elvea.platform.core.security.SecurityConstants.API_REQUEST_PATH;

/**
 * 授权过滤器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtAuthorizationFiler extends AbstractAuthenticationProcessingFilter {

    private final static AntPathRequestMatcher REQUEST_MATCHER = new AntPathRequestMatcher(API_REQUEST_PATH);

    private final AuthenticationConverter authenticationConverter;

    public JwtAuthorizationFiler(
            AuthenticationManager authenticationManager,
            AuthenticationConverter authenticationConverter
    ) {
        super(REQUEST_MATCHER, authenticationManager);
        this.authenticationConverter = authenticationConverter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = this.authenticationConverter.convert(request);
        return this.getAuthenticationManager().authenticate(authentication);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response) &&
                !SecurityUtils.isUrlMatches(request, API_OPEN_REQUEST_PATH);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest servletRequest,
                                            HttpServletResponse servletResponse,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(servletRequest, servletResponse);
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
