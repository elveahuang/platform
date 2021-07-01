package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.core.security.SecurityUtils;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * JwtAccessTokenAuthenticationConverter
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class JwtAccessTokenAuthenticationConverter implements AuthenticationConverter {

    private final JwtSecurityService jwtSecurityService;

    public JwtAccessTokenAuthenticationConverter(JwtSecurityService jwtSecurityService) {
        this.jwtSecurityService = jwtSecurityService;
    }

    @Override
    public JwtAccessTokenAuthenticationToken convert(HttpServletRequest request) {
        try {
            String accessToken = SecurityUtils.getAccessToken(request);
            JWTClaimsSet claimsSet = this.jwtSecurityService.parseAccessToken(accessToken);
            String username = claimsSet.getStringClaim(JwtUtils.JWT_KEY_ACCOUNT);
            return new JwtAccessTokenAuthenticationToken(username, accessToken);
        } catch (Exception e) {
            log.error("Failed to parse accessToken.", e);
            throw new InvalidRequestException("Invalid Request.");
        }
    }

}
