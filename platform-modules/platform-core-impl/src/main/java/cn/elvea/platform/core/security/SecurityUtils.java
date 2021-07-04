package cn.elvea.platform.core.security;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

import static cn.elvea.platform.core.security.SecurityConstants.AUTHORIZATION_HEADER_NAME;
import static cn.elvea.platform.core.security.SecurityConstants.AUTHORIZATION_PREFIX;

/**
 * SecurityUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class SecurityUtils {

    public static String getAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER_NAME);
        if (StringUtils.isBlank(authorization)) {
            throw new AuthenticationServiceException("Authorization cannot be blank.");
        }
        if (authorization.length() < AUTHORIZATION_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }
        return authorization.substring(AUTHORIZATION_PREFIX.length());
    }

    public static String getAccessTokenCode(HttpServletRequest request) {
        String authorization = ServletUtils.obtainRequestParameter(request, "code", "");
        if (StringUtils.isBlank(authorization)) {
            throw new AuthenticationServiceException("Authorization cannot be blank.");
        }
        return authorization;
    }

    /**
     * 未登陆则表明是匿名用户
     *
     * @return boolean
     */
    public static boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    /**
     * 工具类，验证当前请求连接是否匹配
     *
     * @param request 请求
     * @param pattern 连接
     * @return boolean
     */
    public static boolean isUrlMatches(HttpServletRequest request, String pattern) {
        return isUrlMatches(request, pattern, null);
    }

    /**
     * 工具类，验证当前请求连接是否匹配
     *
     * @param request 请求
     * @param pattern 连接
     * @return boolean
     */
    public static boolean isUrlMatches(HttpServletRequest request, String pattern, String httpMethod) {
        RequestMatcher requestMatcher = new AntPathRequestMatcher(pattern, httpMethod);
        return requestMatcher.matches(request);
    }

}
