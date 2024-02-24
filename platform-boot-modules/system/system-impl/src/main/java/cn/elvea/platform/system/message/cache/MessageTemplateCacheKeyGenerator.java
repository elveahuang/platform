package cn.elvea.platform.system.message.cache;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.cache.CacheKeyGenerator;
import org.jetbrains.annotations.NotNull;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.MESSAGE_TEMPLATE;

/**
 * @author elvea
 * @since 24.1.0
 */
public class MessageTemplateCacheKeyGenerator implements CacheKeyGenerator {

    @Override
    public @NotNull String getPrefix() {
        return MESSAGE_TEMPLATE;
    }

    public CacheKey byType(Long typeId, Long templateTypeId) {
        return this.key("code", typeId, templateTypeId);
    }

}
