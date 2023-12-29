package cn.elvea.platform.commons.core.cache.utils;

import cn.elvea.platform.commons.core.enums.RateLimitTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class RedissonUtils {

    private final RedissonClient client;

    public long rateLimiter(String key, RateLimitTypeEnum type, long rate, long rateInterval) {
        RateType rateType = RateType.OVERALL;
        if (type == RateLimitTypeEnum.CLUSTER) {
            rateType = RateType.PER_CLIENT;
        }

        RRateLimiter rateLimiter = this.client.getRateLimiter(key);
        rateLimiter.trySetRate(rateType, rate, rateInterval, RateIntervalUnit.SECONDS);
        if (rateLimiter.tryAcquire()) {
            return rateLimiter.availablePermits();
        } else {
            return -1L;
        }
    }

}
