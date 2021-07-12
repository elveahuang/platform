package cn.elvea.platform.commons.utils;


import cn.elvea.platform.base.constants.GlobalConstants;
import cn.elvea.platform.base.constants.StringConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ServletUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class ServletUtils {

    private final static String UNKNOWN_IP = "unknown";
    private final static String LOCAL_IP = "127.0.0.1";

    public static final String TEXT_CONTENT_TYPE = "text/plain";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String XML_CONTENT_TYPE = "text/xml";
    public static final String HTML_CONTENT_CTYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";

    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取HttpSession
     */
    public static HttpSession getSession() {
        return getRequestAttributes().getRequest().getSession();
    }

    /**
     * 获取ServletRequestAttributes
     */
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 获取客户端IP
     *
     * @return String
     */
    public static String getHost() {
        return getHost(getRequest());
    }

    /**
     * 获取客户端IP
     *
     * @return String
     */
    public static String getHost(HttpServletRequest request) {
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

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 获取参数值
     *
     * @param request       {@link HttpServletRequest}
     * @param parameterName 参数名
     * @return 参数值
     */
    public static String getParameter(HttpServletRequest request, String parameterName) {
        return getParameter(request, parameterName, "");
    }

    /**
     * 获取参数值
     *
     * @param request               {@link HttpServletRequest}
     * @param parameterName         参数名
     * @param defaultParameterValue 默认参数值
     * @return 参数值
     */
    public static String getParameter(HttpServletRequest request, String parameterName, String defaultParameterValue) {
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
     * 设置Etag Header.
     */
    public static void setEtag(HttpServletResponse response, String etag) {
        response.setHeader("ETag", etag);
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

    private static HttpServletResponse initResponseHeader(HttpServletResponse response, final String contentType, final String... headers) {
        response.setContentType(contentType);
        response.setCharacterEncoding(GlobalConstants.ENCODING);
        ServletUtils.setNoCacheHeader(response);
        return response;
    }

    /**
     * 把指定内容渲染到客户端
     *
     * @param response    {@link HttpServletResponse}
     * @param contentType 内容类型
     * @param content     内容
     * @param headers     头
     */
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
        render(response, TEXT_CONTENT_TYPE, text);
    }

    /**
     * 直接输出HTML
     */
    public static void renderHtml(HttpServletResponse response, final String html, final String... headers) {
        render(response, HTML_CONTENT_CTYPE, html, headers);
    }

    /**
     * 直接输出XML
     */
    public static void renderXml(HttpServletResponse response, final String xml, final String... headers) {
        render(response, XML_CONTENT_TYPE, xml, headers);
    }

    /**
     * 直接输出JSON
     */
    public static void renderJson(HttpServletResponse response, final Object data, final String... headers) {
        try {
            render(response, JSON_CONTENT_TYPE, JsonUtils.toJson(data), headers);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
