package cn.elvea.platform.commons.autoconfigure.logging;

import cn.elvea.platform.commons.logging.aspect.OptLogAspect;
import cn.elvea.platform.commons.logging.provider.DefaultLoggingProvider;
import cn.elvea.platform.commons.logging.provider.LoggingProviderManager;
import cn.elvea.platform.commons.logging.provider.LoggingProviderManagerCustomizer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * LoggingAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@EnableAsync
@Configuration
@EnableConfigurationProperties(LoggingConfigProperties.class)
public class LoggingAutoConfiguration {

    private final LoggingConfigProperties config;

    public LoggingAutoConfiguration(LoggingConfigProperties config) {
        this.config = config;
    }

    @Bean
    @ConditionalOnMissingBean
    public OptLogAspect optLogAspect(LoggingProviderManager manager) {
        return new OptLogAspect(manager);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoggingProviderManager loggingProviderManager(ObjectProvider<LoggingProviderManagerCustomizer> customizers) {
        LoggingProviderManager loggingProviderManager = new LoggingProviderManager(
                Lists.newArrayList(new DefaultLoggingProvider())
        );
        customizers.orderedStream().forEach((customizer) -> customizer.customize(loggingProviderManager));
        return loggingProviderManager;
    }

}
