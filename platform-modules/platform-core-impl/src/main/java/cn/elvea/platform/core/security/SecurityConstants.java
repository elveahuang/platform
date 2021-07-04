package cn.elvea.platform.core.security;

/**
 * JwtSecurityConstants
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class SecurityConstants {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public final static String AUTHORIZATION_PREFIX = "Bearer ";

    // =================================================================================================================
    //
    // =================================================================================================================

    public static final String API_AUTH_TOKEN_PATH = "/api/auth/token";
    public static final String API_AUTH_LOGOUT_PATH = "/api/auth/logout";
    public static final String API_REQUEST_PATH = "/api/**";
    public static final String API_OPEN_REQUEST_PATH = "/api/open/**";
    public static final String WEB_SOCKET_REQUEST_PATH = "/web-socket/**";

    /**
     * =================================================================================================================
     * 忽略权限检查的地址
     * =================================================================================================================
     */

    public static final String[] API_EXCLUDE_URLS = {
            API_AUTH_TOKEN_PATH,
            API_AUTH_LOGOUT_PATH,
            API_REQUEST_PATH,
            API_OPEN_REQUEST_PATH,
            WEB_SOCKET_REQUEST_PATH,
    };

}
