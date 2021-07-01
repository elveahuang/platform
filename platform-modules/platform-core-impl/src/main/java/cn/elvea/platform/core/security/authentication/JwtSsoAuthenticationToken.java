package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.jwt.JwtGrantAuthenticationToken;
import org.springframework.util.Assert;

/**
 * JwtSsoAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtSsoAuthenticationToken extends JwtGrantAuthenticationToken {

    private final String code;

    public JwtSsoAuthenticationToken(String code) {
        super(SecurityGrantTypeEnum.SSO.getValue());
        Assert.hasText(code, "code cannot be empty");
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
