package cn.elvea.platform.system.keyword.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.keyword.model.entity.KeywordEntity;
import cn.elvea.platform.system.keyword.repository.KeywordRepository;
import cn.elvea.platform.system.keyword.service.KeywordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.system.commons.constants.SystemCacheConstants.KEYWORD;

/**
 * @author elvea
 * @see KeywordService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Slf4j
@Service
public class KeywordServiceImpl extends BaseCachingEntityService<KeywordEntity, Long, KeywordRepository> implements KeywordService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(KEYWORD).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
