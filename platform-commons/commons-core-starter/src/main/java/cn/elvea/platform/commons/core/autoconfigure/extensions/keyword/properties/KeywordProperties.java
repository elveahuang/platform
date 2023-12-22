package cn.elvea.platform.commons.core.autoconfigure.extensions.keyword.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(KeywordProperties.PREFIX)
public class KeywordProperties {

    public static final String PREFIX = "platform.keyword";

    private boolean enabled = false;

}
