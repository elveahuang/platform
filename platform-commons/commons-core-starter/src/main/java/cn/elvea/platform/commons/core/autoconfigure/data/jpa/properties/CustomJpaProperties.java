package cn.elvea.platform.commons.core.autoconfigure.data.jpa.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomJpaProperties.PREFIX)
public class CustomJpaProperties {

    public static final String PREFIX = "platform.data.jpa";

    private boolean enabled = false;

    private boolean showSql = false;

}
