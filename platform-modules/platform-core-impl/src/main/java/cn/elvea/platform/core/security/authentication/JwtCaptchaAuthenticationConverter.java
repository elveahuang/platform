package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
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
    public JwtCaptchaAuthenticationToken convert(HttpServletRequest request) {
        String grantType = ServletUtils.getParameter(request, "grant_type");
        if (!SecurityGrantTypeEnum.isValidGrantType(grantType, SecurityGrantTypeEnum.CAPTCHA)) {
            throw new InvalidGrantTypeException();
        }
        String username = ServletUtils.getParameter(request, "username");
        String captchaKey = ServletUtils.getParameter(request, "captcha_key");
        String captchaCode = ServletUtils.getParameter(request, "captcha_code");
        return new JwtCaptchaAuthenticationToken(username, captchaKey, captchaCode);
    }

}
