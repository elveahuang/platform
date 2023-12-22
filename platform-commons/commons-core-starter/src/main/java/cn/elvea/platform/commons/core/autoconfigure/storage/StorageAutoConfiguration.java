package cn.elvea.platform.commons.core.autoconfigure.storage;

import cn.elvea.platform.commons.core.autoconfigure.storage.properties.StorageProperties;
import cn.elvea.platform.commons.core.storage.StorageConfig;
import cn.elvea.platform.commons.core.storage.manager.DefaultStorageManager;
import cn.elvea.platform.commons.core.storage.manager.StorageManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(StorageProperties.class)
@ConditionalOnProperty(prefix = StorageProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class StorageAutoConfiguration {

    public StorageAutoConfiguration(StorageProperties properties) {
        log.info("StorageAutoConfiguration is enabled.");
        log.info("Current Storage is {}", properties.getType());
        log.info("Local Storage is {}", properties.getLocal().isEnabled());
        log.info("Min Storage is {}", properties.getMin().isEnabled());
        log.info("COS Storage is {}", properties.getCos().isEnabled());
        log.info("OSS Storage is {}", properties.getOss().isEnabled());
    }

    /**
     * @return {@link StorageManager}
     */
    @Bean
    @ConditionalOnMissingBean
    StorageManager storageManager(StorageProperties properties) {
        StorageConfig config = StorageConfig.builder()
                .type(properties.getType())
                .cos(properties.getCos())
                .oss(properties.getOss())
                .min(properties.getMin())
                .local(properties.getLocal())
                .build();
        return new DefaultStorageManager(config);
    }

}
