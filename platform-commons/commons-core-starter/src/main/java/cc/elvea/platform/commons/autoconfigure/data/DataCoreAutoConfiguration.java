package cc.elvea.platform.commons.autoconfigure.data;

import cc.elvea.platform.commons.autoconfigure.data.properties.DataCoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static cc.elvea.platform.commons.autoconfigure.data.properties.DataCoreProperties.PREFIX;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DataCoreProperties.class)
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class DataCoreAutoConfiguration {

    public DataCoreAutoConfiguration() {
        log.info("DataCustomAutoConfiguration is enabled.");
    }


}
