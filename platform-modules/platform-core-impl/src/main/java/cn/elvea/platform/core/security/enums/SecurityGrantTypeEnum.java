package cn.elvea.platform.core.security.enums;

import cn.elvea.platform.base.enums.BaseEnum;

/**
 * 安全认证类型
 *
 * @author elvea
 * @since 0.0.1
 */
public enum SecurityGrantTypeEnum implements BaseEnum<String> {
    SSO("SSO", "单点登录模式"),
    PASSWORD("PASSWORD", "密码模式"),
    CAPTCHA("CAPTCHA", "验证码模式"),
    REFRESH_TOKEN("REFRESH_TOKEN", "刷新凭证"),
    WECHAT("WECHAT", "微信"),
    WECOM("WECOM", "企业微信");

    private final String code;
    private final String description;

    SecurityGrantTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static SecurityGrantTypeEnum getGrantType(String code) {
        SecurityGrantTypeEnum[] ts = SecurityGrantTypeEnum.values();
        for (SecurityGrantTypeEnum t : ts) {
            if (t.getValue().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return PASSWORD;
    }

    public static boolean isValidGrantType(String code) {
        SecurityGrantTypeEnum[] ts = SecurityGrantTypeEnum.values();
        for (SecurityGrantTypeEnum t : ts) {
            if (t.getValue().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidGrantType(String code, SecurityGrantTypeEnum expectedGrantType) {
        return isValidGrantType(code) && getGrantType(code).equals(expectedGrantType);
    }

    @Override
    public String getValue() {
        return code;
    }

    @Override
    public String getLabel() {
        return "label_security_grant_type__" + this.code.toLowerCase();
    }

    @Override
    public String getDescription() {
        return description;
    }

}
