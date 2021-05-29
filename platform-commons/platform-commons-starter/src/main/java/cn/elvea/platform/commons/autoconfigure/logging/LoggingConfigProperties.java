package cn.elvea.platform.commons.autoconfigure.logging;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * LoggingConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties("platform.logging")
public class LoggingConfigProperties {
}
