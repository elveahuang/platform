package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.entity.OperatorEntity;
import cn.elvea.platform.system.core.repository.OperatorRepository;
import cn.elvea.platform.system.config.service.ConfigService;
import cn.elvea.platform.system.core.service.OperatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ConfigService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Service
@AllArgsConstructor
public class OperatorServiceImpl extends BaseCachingEntityService<OperatorEntity, Long, OperatorRepository> implements OperatorService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.ACTOR).build();

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

}
