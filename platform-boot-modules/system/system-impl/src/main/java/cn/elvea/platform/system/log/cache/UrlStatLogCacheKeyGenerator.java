package cn.elvea.platform.system.log.cache;

import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.URL_STAT;

/**
 * @author elvea
 * @since 24.1.0
 */
public class UrlStatLogCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return URL_STAT;
    }

}
