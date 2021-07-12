package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.commons.utils.ServletUtils;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.exception.InvalidGrantTypeException;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtRefreshTokenAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtRefreshTokenAuthenticationConverter implements AuthenticationConverter {

    private final JwtSecurityService jwtSecurityService;

    public JwtRefreshTokenAuthenticationConverter(JwtSecurityService jwtSecurityService) {
        this.jwtSecurityService = jwtSecurityService;
    }

    @Override
    public JwtRefreshTokenAuthenticationToken convert(HttpServletRequest request) {
        try {
            String grantType = ServletUtils.getParameter(request, "grant_type");
            if (!SecurityGrantTypeEnum.isValidGrantType(grantType, SecurityGrantTypeEnum.REFRESH_TOKEN)) {
                throw new InvalidGrantTypeException();
            }
            String refreshToken = ServletUtils.getParameter(request, "refresh_token");
            JWTClaimsSet claimsSet = this.jwtSecurityService.parseRefreshToken(refreshToken);
            String username = claimsSet.getStringClaim(JwtUtils.JWT_KEY_ACCOUNT);
            String sessionId = claimsSet.getStringClaim(JwtUtils.JWT_KEY_SESSION_ID);
            return new JwtRefreshTokenAuthenticationToken(username, refreshToken, sessionId);
        } catch (Exception e) {
            throw new InvalidRequestException();
        }
    }

}
