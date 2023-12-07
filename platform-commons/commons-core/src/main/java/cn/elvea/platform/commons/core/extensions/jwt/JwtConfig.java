package cn.elvea.platform.commons.core.extensions.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {

    @Builder.Default
    private Boolean enabled = Boolean.FALSE;

    @Builder.Default
    private String algorithm = JwsAlgorithms.RS256;

    private String publicKeyValue;

    private String privateKeyValue;

    @Builder.Default
    private Duration authorizationCodeTimeToLive = Duration.ofMinutes(5);

    @Builder.Default
    private Duration deviceCodeTimeToLive = Duration.ofMinutes(5);

    @Builder.Default
    private Duration accessTokenTimeToLive = Duration.ofMinutes(15);

    @Builder.Default
    private Duration refreshTokenTimeToLive = Duration.ofDays(3);

}
