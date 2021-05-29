package cn.elvea.platform.commons.autoconfigure.jwt;

import cn.elvea.platform.commons.jwt.JwtConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JwtConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("platform.jwt")
public class JwtConfigProperties extends JwtConfig {
}
