package cc.elvea.platform.commons.autoconfigure.data.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(DataCoreProperties.PREFIX)
public class DataCoreProperties {

    public static final String PREFIX = "platform.data.core";

    private boolean enabled = false;

}
