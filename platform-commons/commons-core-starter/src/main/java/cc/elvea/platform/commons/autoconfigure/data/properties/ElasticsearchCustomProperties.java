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
@ConfigurationProperties(ElasticsearchCustomProperties.PREFIX)
public class ElasticsearchCustomProperties {

    public static final String PREFIX = "platform.data.elasticsearch";

    private boolean enabled = false;

}
