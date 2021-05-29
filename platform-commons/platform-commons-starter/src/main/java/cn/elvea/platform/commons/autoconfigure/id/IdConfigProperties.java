package cn.elvea.platform.commons.autoconfigure.id;

import cn.elvea.platform.commons.id.IdConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * IdConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("platform.id")
public class IdConfigProperties extends IdConfig {
}
