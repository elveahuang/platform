package cc.elvea.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = CacheCustomProperties.PREFIX)
public class CacheCustomProperties {

    public static final String PREFIX = "platform.cache";

    private boolean enabled = false;

    private boolean cacheNullValue = true;

    private int batchSize = 1000;

    private Duration timeToLive = Duration.ofHours(1);

}
