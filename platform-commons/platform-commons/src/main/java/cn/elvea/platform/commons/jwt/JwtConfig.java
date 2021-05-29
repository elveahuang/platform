package cn.elvea.platform.commons.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.elvea.platform.commons.utils.JwtUtils.JWT_SECRET;

/**
 * JwtConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class JwtConfig {

    private String secret = JWT_SECRET;

    private Long accessTokenExpire = 7200L;

    private Long refreshTokenExpire = 28800L;

}
