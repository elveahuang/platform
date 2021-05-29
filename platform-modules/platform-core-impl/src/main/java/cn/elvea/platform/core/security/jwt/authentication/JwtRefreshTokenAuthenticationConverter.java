package cn.elvea.platform.core.security.jwt.authentication;

import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.jwt.JwtSecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtRefreshTokenAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtRefreshTokenAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = ServletUtils.obtainRequestParameter(request, "grant_type");
        if (!JwtSecurityConstants.REFRESH_TOKEN.equalsIgnoreCase(grantType)) {
            return null;
        }
        String refreshToken = ServletUtils.obtainRequestParameter(request, "refresh_token");
        return new JwtRefreshTokenAuthenticationToken(refreshToken);
    }

}
