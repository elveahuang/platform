package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.jwt.JwtGrantAuthenticationToken;

/**
 * JwtPasswordAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtPasswordAuthenticationToken extends JwtGrantAuthenticationToken {

    public JwtPasswordAuthenticationToken(String principal, String credentials) {
        super(principal, credentials, SecurityGrantTypeEnum.PASSWORD.getValue());
    }

}
