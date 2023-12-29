package cn.elvea.platform.commons.core.autoconfigure.extensions.limit;

import cn.elvea.platform.commons.core.autoconfigure.core.CoreAutoConfiguration;
import cn.elvea.platform.commons.core.autoconfigure.extensions.limit.properties.RateLimitProperties;
import cn.elvea.platform.commons.core.cache.service.CacheService;
import cn.elvea.platform.commons.core.extensions.limit.RateLimitAspect;
import lombok.extern.slf4j.Slf4j;
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
 * @since 0.0.1
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(CoreAutoConfiguration.class)
@EnableConfigurationProperties(RateLimitProperties.class)
@ConditionalOnProperty(prefix = RateLimitProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class RateLimitAutoConfiguration {

    public RateLimitAutoConfiguration() {
        log.info("RateLimitAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(CacheService.class)
    public RateLimitAspect rateLimitAspect(CacheService cacheService) {
        log.info("Creating RateLimitAspect...");
        return new RateLimitAspect(cacheService);
    }

}
