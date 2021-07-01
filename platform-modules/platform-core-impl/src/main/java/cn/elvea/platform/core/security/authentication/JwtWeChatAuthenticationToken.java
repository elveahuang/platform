package cn.elvea.platform.core.security.authentication;

import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.jwt.JwtGrantAuthenticationToken;
import org.springframework.util.Assert;

/**
 * JwtWeChatAuthenticationToken
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtWeChatAuthenticationToken extends JwtGrantAuthenticationToken {

    private final String code;

    public JwtWeChatAuthenticationToken(String code) {
        super(SecurityGrantTypeEnum.WECHAT.getValue());
        Assert.hasText(code, "code cannot be empty");
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
