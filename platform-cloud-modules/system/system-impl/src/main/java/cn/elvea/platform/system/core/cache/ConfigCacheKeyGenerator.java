package cn.elvea.platform.system.core.cache;

import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * ConfigCacheKeyGenerator
 *
 * @author elvea
 * @since 24.1.0
 */
public class ConfigCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.CONFIG;
    }

}
