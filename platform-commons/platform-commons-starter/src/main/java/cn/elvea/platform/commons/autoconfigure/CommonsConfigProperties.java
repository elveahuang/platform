package cn.elvea.platform.commons.autoconfigure;

import cn.elvea.platform.commons.config.Config;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "platform.commons")
public class CommonsConfigProperties extends Config {
}
