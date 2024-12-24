package cc.elvea.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(QuartzCustomProperties.PREFIX)
public class QuartzCustomProperties {

    public static final String PREFIX = "platform.quartz";

    private boolean enabled = true;

}
