package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.jwt.JwtSecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtCaptchaAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtCaptchaAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
        if (!JwtSecurityConstants.CAPTCHA.equalsIgnoreCase(grantType)) {
            return null;
        }
        String username = ServletUtils.obtainRequestParameter(request, "username");
        String captchaKey = ServletUtils.obtainRequestParameter(request, "captcha_key");
        String captcha = ServletUtils.obtainRequestParameter(request, "captcha");
        return new JwtCaptchaAuthenticationToken(username, captchaKey, captcha);
    }

}
