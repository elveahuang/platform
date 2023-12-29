package cn.elvea.platform.commons.core.enums;

import lombok.Getter;

/**
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum ResponseCodeEnum implements BaseEnum<String> {
    // 框架基础
    SUCCESS("200", "", "正确执行并成功返回"),
    UNAUTHORIZED("401", "", "未授权"),
    FORBIDDEN("403", "", "访问未授权"),
    NOT_FOUNT("404", "Not Found.", "请求地址不存在"),
    // 系统基础
    ERROR("10000000", "System Exception", "系统错误"),
    PARAM_ERROR("10000001", "Param Error.", "参数检查不通过"),
    PARAM_NOT_PRESENT("10000002", "Param is not present.", "参数不能为空"),
    INVALID_CAPTCHA("10000003", "Incorrect Captcha.", "验证码错误或者验证码已经过期"),
    RATE_LIMIT_ERROR("10000004", "Rate Limit.", "限流"),
    // 用户模块
    USER__USERNAME_NOT_AVAILABLE("10010001", "Param is not present.", "用户名不可用"),
    USER__EMAIL_NOT_AVAILABLE("10010002", "Param is not present.", "邮箱不可用"),
    USER__MOBILE_NOT_AVAILABLE("10010003", "Param is not present.", "手机号码不可用"),
    USER__INVALID_USERNAME_OR_PASSWORD("10010004", "Invalid Username or Password.", "用户名或者密码不正确");

    private final String code;
    private final String description;
    private final String message;

    ResponseCodeEnum(final String code, final String message, final String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return ("label_response_code__").concat(this.code.toLowerCase());
    }

}
