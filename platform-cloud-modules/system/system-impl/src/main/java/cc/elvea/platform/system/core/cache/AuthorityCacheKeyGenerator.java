package cc.elvea.platform.system.core.cache;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * AuthorityCacheKeyGenerator
 *
 * @author elvea
 * @since 24.1.0
 */
public class AuthorityCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.AUTHORITY;
    }

}