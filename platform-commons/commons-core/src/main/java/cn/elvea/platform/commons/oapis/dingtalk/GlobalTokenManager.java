package cn.elvea.platform.commons.oapis.dingtalk;

import cn.elvea.platform.commons.oapis.dingtalk.cache.LocalCache;
import cn.elvea.platform.commons.oapis.dingtalk.token.TokenManager;

/**
 * @author elvea
 * @since 24.1.0
 */
public class GlobalTokenManager {

    private static volatile TokenManager globalTokenManager = new TokenManager(LocalCache.getInstance());

    public static TokenManager getTokenManager() {
        return globalTokenManager;
    }

    public static void setTokenManager(TokenManager tokenManager) {
        globalTokenManager = tokenManager;
    }

}
