package cn.elvea.platform.system.dict.cache;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DictTypeCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.DICT_TYPE;
    }

    public CacheKey keyByCode(String key) {
        return this.key("key", key);
    }

}
