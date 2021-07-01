package cn.elvea.platform.core.security.jwt;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * JwtAccessTokenResponse
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class JwtAuthenticationResponse implements Serializable {
    private String access_token;
    private String refresh_token;
}
