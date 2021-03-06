package cn.elvea.platform.core.security.jwt;

import cn.elvea.platform.commons.jwt.JwtService;
import cn.elvea.platform.commons.utils.JwtUtils;
import cn.elvea.platform.core.security.SecurityUser;
import cn.elvea.platform.core.security.exception.InvalidRequestException;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

import static cn.elvea.platform.commons.utils.JwtUtils.*;

/**
 * 安全服务
 *
 * @author elvea
 * @since 0.0.1
 */
@Service
public class JwtSecurityService {

    private final JwtService jwtService;

    public JwtSecurityService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Create Access Token
     *
     * @param user {@link SecurityUser}
     * @return Access Token
     */
    public String createAccessToken(String uuid, SecurityUser user) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationMillis = currentTimeMillis + this.jwtService.getConfig().getAccessTokenExpire() * 1000;
        Date now = new Date(currentTimeMillis);
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        builder.jwtID(uuid)
                .subject(user.getUsername())
                .claim(JWT_KEY_SESSION_ID, uuid)
                .claim(JWT_KEY_TOKEN_TYPE, JwtUtils.JWT_TOKEN_TYPE_ACCESS_TOKEN)
                .claim(JwtUtils.JWT_KEY_UID, user.getId())
                .claim(JwtUtils.JWT_KEY_ACCOUNT, user.getUsername());
        JWTClaimsSet claimsSet = builder
                .expirationTime(new Date(expirationMillis))
                .issueTime(now)
                .notBeforeTime(now).build();
        return this.jwtService.createJwsToken(claimsSet);
    }

    /**
     * 解析Access Token
     *
     * @param token Access Token
     * @return {@link JWTClaimsSet}
     */
    public JWTClaimsSet parseAccessToken(String token) throws Exception {
        JWTClaimsSet claimsSet = this.jwtService.parseJwsToken(token);
        if (!JWT_TOKEN_TYPE_ACCESS_TOKEN.equalsIgnoreCase(claimsSet.getStringClaim(JWT_KEY_TOKEN_TYPE))) {
            throw new InvalidRequestException("Invalid Request.");
        }
        return claimsSet;
    }

    /**
     * 创建Refresh Token
     *
     * @param user {@link SecurityUser}
     * @return Refresh Token
     */
    public String createRefreshToken(String uuid, SecurityUser user) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationMillis = currentTimeMillis + this.jwtService.getConfig().getRefreshTokenExpire() * 1000;
        Date now = new Date(currentTimeMillis);
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        builder.jwtID(uuid)
                .subject(user.getUsername())
                .claim(JWT_KEY_SESSION_ID, uuid)
                .claim(JWT_KEY_TOKEN_TYPE, JwtUtils.JWT_TOKEN_TYPE_REFRESH_TOKEN)
                .claim(JwtUtils.JWT_KEY_UID, user.getId())
                .claim(JwtUtils.JWT_KEY_ACCOUNT, user.getUsername());
        JWTClaimsSet claimsSet = builder
                .expirationTime(new Date(expirationMillis))
                .issueTime(now)
                .notBeforeTime(now).build();
        return this.jwtService.createJwsToken(claimsSet);
    }

    /**
     * 解析Refresh Token
     *
     * @param token Refresh Token
     * @return {@link JWTClaimsSet}
     */
    public JWTClaimsSet parseRefreshToken(String token) throws Exception {
        JWTClaimsSet claimsSet = this.jwtService.parseJwsToken(token);
        if (!JWT_TOKEN_TYPE_REFRESH_TOKEN.equalsIgnoreCase(claimsSet.getStringClaim(JWT_KEY_TOKEN_TYPE))) {
            throw new InvalidRequestException("Invalid Request.");
        }
        return claimsSet;
    }

    public Authentication getAuthentication(String token) throws Exception {
        JWTClaimsSet claimsSet = JwtUtils.parseJwsToken(token);
        String jwtId = claimsSet.getJWTID();
        return null;
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private SecurityUser getCurrentSecurityUser() {
        Authentication authentication = getCurrentAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof SecurityUser) {
                return (SecurityUser) authentication.getPrincipal();
            }
        }
        return null;
    }

    private String getCurrentUser() {
        Authentication authentication = getCurrentAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            if (authentication.getPrincipal() instanceof SecurityUser) {
                return ((SecurityUser) authentication.getPrincipal()).getUsername();
            } else if (authentication.getPrincipal() instanceof User) {
                return ((User) authentication.getPrincipal()).getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                return (String) authentication.getPrincipal();
            }
        }
        return null;
    }

}
