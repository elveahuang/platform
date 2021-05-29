package cn.elvea.platform.core.system.enums;

import lombok.Getter;

/**
 * 登录验证方式
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum AuthType {
    /**
     * 页面表单登录
     */
    WEB("WEB", true, "label_auth_type_web"),
    /**
     * 接口登录
     */
    API("API", true, "label_auth_type_api"),
    /**
     * 单点登录
     */
    SSO("SSO", false, "label_auth_type_sso"),
    /**
     * 凭证认证
     */
    TOKEN("TOKEN", false, "label_auth_type_token");

    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String desc;
    /**
     * 是否需要进行密码验证
     */
    private final boolean needCheckPassword;

    AuthType(final String type, final boolean needCheckPassword, final String desc) {
        this.type = type;
        this.desc = desc;
        this.needCheckPassword = needCheckPassword;
    }

    /**
     * 根据类型编码获取对应的角色类型
     */
    public static AuthType getAuthType(String type) {
        AuthType[] ts = AuthType.values();
        for (AuthType t : ts) {
            if (t.getType().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return WEB;
    }

}
