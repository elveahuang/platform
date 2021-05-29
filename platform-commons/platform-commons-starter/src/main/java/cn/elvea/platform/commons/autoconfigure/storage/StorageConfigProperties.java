package cn.elvea.platform.commons.autoconfigure.storage;

import cn.elvea.platform.commons.storage.StorageConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * StorageConfigProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "platform.storage")
public class StorageConfigProperties extends StorageConfig {
}
