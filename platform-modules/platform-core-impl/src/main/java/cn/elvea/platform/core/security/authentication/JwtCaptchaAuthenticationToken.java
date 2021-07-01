package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.jwt.JwtGrantAuthenticationToken;
import org.springframework.util.Assert;

/**
 * JwtCaptchaAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtCaptchaAuthenticationToken extends JwtGrantAuthenticationToken {

    private final String captchaKey;

    private final String captchaCode;

    public JwtCaptchaAuthenticationToken(String principal, String captchaKey, String captchaCode) {
        super(principal, "", SecurityGrantTypeEnum.CAPTCHA.getValue());
        Assert.hasText(captchaKey, "captchaKey cannot be empty");
        Assert.hasText(captchaCode, "captchaCode cannot be empty");
        this.captchaKey = captchaKey;
        this.captchaCode = captchaCode;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

}
