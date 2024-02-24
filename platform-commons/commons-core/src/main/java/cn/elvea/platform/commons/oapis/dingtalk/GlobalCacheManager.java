package cn.elvea.platform.commons.oapis.dingtalk;

import cn.elvea.platform.commons.oapis.dingtalk.cache.Cache;
import cn.elvea.platform.commons.oapis.dingtalk.cache.LocalCache;

/**
 * @author elvea
 * @since 24.1.0
 */
public class GlobalCacheManager {

    private static volatile Cache globalCache = LocalCache.getInstance();

    public static Cache getCache() {
        return globalCache;
    }

    public static void setCache(Cache cache) {
        globalCache = cache;
    }

}
