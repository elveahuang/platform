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
@ConfigurationProperties(JpaCustomProperties.PREFIX)
public class JpaCustomProperties {

    public static final String PREFIX = "platform.data.jpa";

    private boolean enabled = false;

    private boolean showSql = true;

    private boolean formatSql = true;

}
