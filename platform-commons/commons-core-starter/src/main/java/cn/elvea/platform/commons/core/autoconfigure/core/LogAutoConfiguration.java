package cn.elvea.platform.commons.core.autoconfigure.core;

import cn.elvea.platform.commons.core.autoconfigure.core.CoreAutoConfiguration;
import cn.elvea.platform.commons.core.autoconfigure.core.properties.LogProperties;
import cn.elvea.platform.commons.core.logging.aspect.OperationLogAspect;
import cn.elvea.platform.commons.core.logging.store.DefaultLogStore;
import cn.elvea.platform.commons.core.logging.store.LogStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(CoreAutoConfiguration.class)
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    public LogAutoConfiguration() {
        log.info("LogAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogStore logStore() {
        return new DefaultLogStore();
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogAspect optLogAspect(LogStore logStore) {
        log.info("Creating OperationLogAspect...");
        return new OperationLogAspect(logStore);
    }

}
