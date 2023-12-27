package cn.elvea.platform.commons.core.extensions.platform;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author dev
 * @since 0.0.1
 */
public abstract class PlatformHelper {

    public static Platform fromServletRequest() {
        return fromServletRequest(ServletUtils.getRequest());
    }

    public static Platform fromServletRequest(HttpServletRequest request) {
        return fromUserAgent(ServletUtils.getUserAgent(request));
    }

    public static Platform fromUserAgent(String ua) {
        return Platform.builder().ua(ua).uaObject(UserAgentUtil.parse(ua)).build();
    }

}
