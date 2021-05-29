package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.core.security.jwt.JwtSecurityConstants;
import org.springframework.util.Assert;

/**
 * JwtPasswordAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtCaptchaAuthenticationToken extends JwtGrantAuthenticationToken {

    private final Object principal;

    private final String captchaKey;

    private final String captcha;

    public JwtCaptchaAuthenticationToken(String principal, String captchaKey, String captcha) {
        super(JwtSecurityConstants.CAPTCHA);
        Assert.hasText(principal, "principal cannot be empty");
        Assert.hasText(captchaKey, "captchaKey cannot be empty");
        Assert.hasText(captcha, "captcha cannot be empty");
        this.principal = principal;
        this.captchaKey = captchaKey;
        this.captcha = captcha;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public String getCaptcha() {
        return captcha;
    }

}
