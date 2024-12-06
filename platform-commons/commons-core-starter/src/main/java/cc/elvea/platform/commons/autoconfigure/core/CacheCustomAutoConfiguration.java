package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.CacheCustomProperties;
import cc.elvea.platform.commons.core.cache.aspect.RateLimitAspect;
import cc.elvea.platform.commons.core.cache.service.CacheService;
import cc.elvea.platform.commons.core.cache.service.RedissonCacheService;
import cc.elvea.platform.commons.core.cache.utils.RedissonUtils;
import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({RedissonClient.class})
@EnableConfigurationProperties({CacheCustomProperties.class})
@ConditionalOnProperty(prefix = CacheCustomProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class CacheCustomAutoConfiguration {

    public CacheCustomAutoConfiguration() {
        log.info("CacheCustomAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public Codec codec() {
        return new JsonJacksonCodec(JacksonUtils.getCacheObjectMapper());
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonAutoConfigurationCustomizer redissonAutoConfigurationCustomizer(Codec codec) {
        return config -> config.setCodec(codec);
    }

    @Bean
    @ConditionalOnMissingBean(RedissonUtils.class)
    public RedissonUtils redissonUtils(RedissonClient redissonClient) {
        log.info("Creating RedissonUtils...");
        return new RedissonUtils(redissonClient);
    }

    @Bean
    @ConditionalOnMissingBean(RedissonCacheService.class)
    public CacheService cacheService(RedissonClient redissonClient,
                                     RedissonUtils redissonUtils,
                                     CacheCustomProperties properties) {
        log.info("Creating CacheService[RedissonCacheService]...");
        return new RedissonCacheService(redissonClient,
                redissonUtils,
                properties.isCacheNullValue(),
                properties.getBatchSize());
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CacheManager cacheManager(CacheCustomProperties properties, RedissonClient redissonClient, Codec codec) {
        log.info("Creating CacheManager...");
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient);
        cacheManager.setCodec(codec);
        cacheManager.setAllowNullValues(properties.isCacheNullValue());
        return cacheManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(CacheService.class)
    public RateLimitAspect rateLimitAspect(CacheService cacheService) {
        log.info("Creating RateLimitAspect...");
        return new RateLimitAspect(cacheService);
    }

}
