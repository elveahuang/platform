package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtWeComAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtWeComAuthenticationConverter implements AuthenticationConverter {

    @Override
    public JwtWeComAuthenticationToken convert(HttpServletRequest request) {
        String grantType = ServletUtils.getParameter(request, "grant_type");
        if (!SecurityGrantTypeEnum.isValidGrantType(grantType, SecurityGrantTypeEnum.WECOM)) {
            throw new InvalidGrantTypeException();
        }
        String code = ServletUtils.getParameter(request, "code");
        return new JwtWeComAuthenticationToken(code);
    }

}
