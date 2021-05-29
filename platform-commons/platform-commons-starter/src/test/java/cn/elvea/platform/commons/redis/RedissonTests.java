package cn.elvea.platform.commons.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * RedissionTests
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedissonTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void baseTests() {
        Assertions.assertNotNull(redissonClient);
        redissonClient.getKeys().flushall();
    }

}
