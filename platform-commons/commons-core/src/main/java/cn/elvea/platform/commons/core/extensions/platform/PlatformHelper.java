package cn.elvea.platform.commons.core.extensions.platform;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 获取当前客户端环境信息
 * <a href="https://yauaa.basjes.nl/">...</a>
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class PlatformHelper {

    public static Platform fromServletRequest(HttpServletRequest request) {
        return fromUserAgent(ServletUtils.getUserAgent());
    }

    public static Platform fromUserAgent(String ua) {
        return Platform.builder().ua(ua).uaObject(UserAgentUtil.parse(ua)).build();
    }

}
