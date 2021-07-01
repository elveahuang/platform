package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtWeChatAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtWeChatAuthenticationConverter implements AuthenticationConverter {

    @Override
    public JwtWeChatAuthenticationToken convert(HttpServletRequest request) {
        String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
        if (!SecurityGrantTypeEnum.isValidGrantType(grantType, SecurityGrantTypeEnum.WECHAT)) {
            throw new InvalidGrantTypeException();
        }
        String code = ServletUtils.obtainRequestParameter(request, "code");
        return new JwtWeChatAuthenticationToken(code);
    }

}
