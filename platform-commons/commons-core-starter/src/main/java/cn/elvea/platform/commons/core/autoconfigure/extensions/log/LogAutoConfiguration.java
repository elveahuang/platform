package cn.elvea.platform.commons.core.autoconfigure.extensions.log;

import cn.elvea.platform.commons.core.autoconfigure.core.CoreAutoConfiguration;
import cn.elvea.platform.commons.core.autoconfigure.extensions.log.properties.LogProperties;
import cn.elvea.platform.commons.core.extensions.log.LogManager;
import cn.elvea.platform.commons.core.extensions.log.LogCustomizer;
import cn.elvea.platform.commons.core.extensions.log.aspect.OperationLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
    public LogManager logManager(ObjectProvider<LogCustomizer> customizers) {
        LogManager logManager = new LogManager();
        customizers.orderedStream().forEach((customizer) -> customizer.customize(logManager));
        return logManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(LogManager.class)
    public OperationLogAspect optLogAspect(LogManager manager) {
        log.info("Creating OperationLogAspect...");
        return new OperationLogAspect(manager);
    }

}
