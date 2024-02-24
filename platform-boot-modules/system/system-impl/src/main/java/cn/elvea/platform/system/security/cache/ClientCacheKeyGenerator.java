package cn.elvea.platform.system.security.cache;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 24.1.0
 */
public class ClientCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.CLIENT;
    }

    public CacheKey keyById(Long id) {
        return this.key(id);
    }

    public CacheKey keyByClientId(String clientId) {
        return this.key("client_id", clientId);
    }

}
