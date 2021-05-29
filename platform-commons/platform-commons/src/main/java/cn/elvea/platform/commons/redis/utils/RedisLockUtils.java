package cn.elvea.platform.commons.redis.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * RedisLockUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public class RedisLockUtils {

    /**
     * @see RedissonClient
     */
    private final RedissonClient client;

    public RedisLockUtils(RedissonClient client) {
        this.client = client;
    }

    public boolean isEnabled() {
        return this.client != null;
    }

    public <E> void execute(String key, Supplier<E> supplier) {
        if (this.isEnabled()) {
            try {
                this.lock(key, 120);
                supplier.get();
            } finally {
                this.unlock(key);
            }
        } else {
            supplier.get();
        }
    }

    public RLock lock(String lockKey) {
        RLock lock = this.client.getLock(lockKey);
        lock.lock();
        return lock;
    }

    public RLock lock(String lockKey, int leaseTime) {
        RLock lock = this.client.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }

    public RLock lock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = this.client.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = this.client.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void unlock(String lockKey) {
        RLock lock = this.client.getLock(lockKey);
        lock.unlock();
    }

    public void unlock(RLock lock) {
        lock.unlock();
    }

}
