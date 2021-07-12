package cn.elvea.platform.commons.autoconfigure.redis;

import cn.elvea.platform.commons.redis.serializer.RedisObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Custom Redis Configuration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@EnableConfigurationProperties(CustomRedisConfigProperties.class)
@EnableCaching
public class CustomRedisAutoConfiguration {

    public CustomRedisAutoConfiguration() {
        log.debug("CustomRedisAutoConfiguration......");
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisObjectSerializer redisObjectSerializer() {
        return new RedisObjectSerializer();
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisSerializer redisKeySerializer() {
        return new StringRedisSerializer();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
                                                       RedisObjectSerializer valueSerializer,
                                                       StringRedisSerializer keySerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CacheManager cacheManager(RedisConnectionFactory factory,
                                     RedisObjectSerializer valueSerializer,
                                     StringRedisSerializer keySerializer) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer));
        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }

}
