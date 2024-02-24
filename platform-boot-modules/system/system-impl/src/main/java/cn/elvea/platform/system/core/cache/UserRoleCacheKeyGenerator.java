package cn.elvea.platform.system.core.cache;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 24.1.0
 */
public class UserRoleCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.USER_ROLE;
    }

    public CacheKey byUserId(Long userId) {
        return this.key(userId);
    }

    public static CacheKey keyByUserId(Long userId) {
        return new UserRoleCacheKeyGenerator().byUserId(userId);
    }

}
