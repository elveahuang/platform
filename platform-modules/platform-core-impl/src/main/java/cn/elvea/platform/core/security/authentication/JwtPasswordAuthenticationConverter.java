package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtPasswordAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtPasswordAuthenticationConverter implements AuthenticationConverter {

    @Override
    public JwtPasswordAuthenticationToken convert(HttpServletRequest request) {
        String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
        if (!SecurityGrantTypeEnum.isValidGrantType(grantType, SecurityGrantTypeEnum.PASSWORD)) {
            throw new InvalidGrantTypeException();
        }
        String username = ServletUtils.obtainRequestParameter(request, "username");
        String password = ServletUtils.obtainRequestParameter(request, "password");
        return new JwtPasswordAuthenticationToken(username, password);
    }

}
