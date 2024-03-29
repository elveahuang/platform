package cn.elvea.platform.system.security.cache;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import org.jetbrains.annotations.NotNull;

/**
 * @author elvea
 * @since 24.1.0
 */
public class AuthorizationConsentCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return SystemCacheConstants.AUTHORIZATION;
    }

    public CacheKey byKey(String clientId, String principalName) {
        return this.key(clientId, principalName);
    }

}
