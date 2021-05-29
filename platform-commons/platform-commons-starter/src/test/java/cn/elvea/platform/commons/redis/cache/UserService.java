package cn.elvea.platform.commons.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * CacheService
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class UserService {

    public final static String CACHE_KEY = "USER_CACHE_KEY";

    @Cacheable(cacheNames = CACHE_KEY, key = "#id")
    public User get(Long id) {
        log.debug("get user [{}]", id);
        User user = new User();
        user.setId(1L);
        user.setUsername("root");
        user.setDescription("description");
        return new User();
    }

    @CachePut(cacheNames = CACHE_KEY, key = "#result.id")
    public User save(User user) {
        log.debug("get user [{}]", user.getId());
        return user;
    }

}
