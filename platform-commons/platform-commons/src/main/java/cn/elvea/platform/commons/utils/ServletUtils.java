package cn.elvea.platform.commons.utils;


import cn.elvea.platform.base.constants.GlobalConstants;
import cn.elvea.platform.base.constants.StringConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ServletUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class ServletUtils {

    private final static String UNKNOWN_IP = "unknown";
    private final static String LOCAL_IP = "127.0.0.1";

    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";

    //-- 常用数值定义 --//
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

    /**
     * 获取请求对象
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取客户端IP
     *
     * @return String
     */
    public static String getHost() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasText(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (!StringUtils.hasText(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (LOCAL_IP.equals(ip)) {
            try { // 根据网卡取本机配置的IP
                InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割。
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(StringConstants.STR_DELIMITER) > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取参数值
     *
     * @param request       {@link HttpServletRequest}
     * @param parameterName 参数名
     * @return 参数值
     */
    public static String obtainRequestParameter(HttpServletRequest request, String parameterName) {
        return obtainRequestParameter(request, parameterName, "");
    }

    /**
     * 获取参数值
     *
     * @param request               {@link HttpServletRequest}
     * @param parameterName         参数名
     * @param defaultParameterValue 默认参数值
     * @return 参数值
     */
    public static String obtainRequestParameter(HttpServletRequest request, String parameterName, String defaultParameterValue) {
        String parameterValue = request.getParameter(parameterName);
        if (StringUtils.hasLength(parameterValue)) {
            parameterValue = parameterValue.trim();
        } else {
            parameterValue = defaultParameterValue;
        }
        return parameterValue;
    }

    /**
     * 设置客户端缓存过期时间
     *
     * @param response       {@link HttpServletResponse}
     * @param expiresSeconds 过期时间
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header
        response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
        // Http 1.1 header
        response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
    }

    /**
     * 设置客户端无缓存
     *
     * @param response {@link HttpServletResponse}
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader("Expires", 0);
        response.addHeader("Pragma", "no-cache");
        // Http 1.1 header
        response.setHeader("Cache-Control", "no-cache");
    }

    /**
     * 设置最后修改时间
     *
     * @param response         {@link HttpServletResponse}
     * @param lastModifiedDate 最后修改时间
     */
    public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
        response.setDateHeader("Last-Modified", lastModifiedDate);
    }

    public static void render(HttpServletResponse response, final String contentType, final String content, final String... headers) {
        initResponseHeader(response, contentType, headers);
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 直接输出文本
     */
    public static void renderText(HttpServletResponse response, final String text, final String... headers) {
        render(response, ServletUtils.TEXT_TYPE, text);
    }

    /**
     * 直接输出HTML
     */
    public static void renderHtml(HttpServletResponse response, final String html, final String... headers) {
        render(response, ServletUtils.HTML_TYPE, html, headers);
    }

    /**
     * 直接输出XML
     */
    public static void renderXml(HttpServletResponse response, final String xml, final String... headers) {
        render(response, ServletUtils.XML_TYPE, xml, headers);
    }

    /**
     * 直接输出JSON
     */
    public static void renderJson(HttpServletResponse response, final String jsonString, final String... headers) {
        render(response, ServletUtils.JSON_TYPE, jsonString, headers);
    }

    /**
     * 直接输出JSON
     */
    public static void renderJson(HttpServletResponse response, final Object data, final String... headers) {
        initResponseHeader(response, ServletUtils.JSON_TYPE);
        try {
            JsonUtils.getObjectMapper().writeValue(response.getWriter(), data);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static HttpServletResponse initResponseHeader(HttpServletResponse response, final String contentType, final String... headers) {
        String fullContentType = contentType + "; charset=" + GlobalConstants.ENCODING;
        response.setContentType(fullContentType);
        ServletUtils.setNoCacheHeader(response);
        return response;
    }

}
