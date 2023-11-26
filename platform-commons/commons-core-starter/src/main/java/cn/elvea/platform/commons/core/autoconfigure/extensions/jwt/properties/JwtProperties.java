package cn.elvea.platform.commons.core.autoconfigure.extensions.jwt.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "platform.jwt";

    private Boolean enabled = Boolean.FALSE;

    private String algorithm = JwsAlgorithms.RS256;

    private String publicKeyValue;

    private String privateKeyValue;

    private Duration accessTokenTimeToLive = Duration.ofMinutes(5);

    private Duration refreshTokenTimeToLive = Duration.ofDays(14);

}
