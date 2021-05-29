package cn.elvea.platform.commons.redis.serializer;

import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * RedisObjectSerializer
 *
 * @author elvea
 * @since 0.0.1
 */
public class RedisObjectSerializer extends GenericJackson2JsonRedisSerializer {

    public RedisObjectSerializer() {
    }

}
