package cn.elvea.platform.commons.core.autoconfigure.extensions.sensitive.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SensitiveProperties.PREFIX)
public class SensitiveProperties {

    public static final String PREFIX = "platform.sensitive";

    private boolean enabled = true;

}
