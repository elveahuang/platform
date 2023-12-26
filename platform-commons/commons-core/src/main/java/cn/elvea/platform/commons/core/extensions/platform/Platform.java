package cn.elvea.platform.commons.core.extensions.platform;

import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.Builder;

/**
 * @author elvea
 * @since 0.0.1
 */
@Builder
public class Platform {

    private String ua;

    private UserAgent uaObject;

    public Platform parse() {
        this.uaObject = UserAgentUtil.parse(this.ua);
        return this;
    }

    public boolean isMobile() {
        return uaObject.isMobile();
    }

    public boolean isWeChat() {
        return StringUtils.isNotEmpty(ua) && ua.contains("WeChat");
    }

    public boolean isDingTalk() {
        return StringUtils.isNotEmpty(ua) && ua.contains("DingTalk");
    }

    public boolean isLark() {
        return StringUtils.isNotEmpty(ua) && ua.contains("Lark");
    }

}
