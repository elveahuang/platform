package cc.elvea.platform.commons.autoconfigure.data.properties;

import cc.elvea.platform.commons.data.core.auditor.UserAuditorAware;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

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

    @Bean
    @ConditionalOnMissingBean
    public UserAuditorAware userAuditorAware() {
        return new UserAuditorAware();
    }

}
