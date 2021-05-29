package cn.elvea.platform.commons.autoconfigure.redis;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CustomRedisConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties("platform.redis")
public class CustomRedisConfigProperties {
}
