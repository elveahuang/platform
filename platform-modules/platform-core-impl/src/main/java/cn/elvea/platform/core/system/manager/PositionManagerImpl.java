package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.PositionEntity;
import cn.elvea.platform.core.system.manager.PositionManager;
import cn.elvea.platform.core.system.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * PermissionManager
 *
 * @author elvea
 * @see PositionManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SystemConstants.CACHE_POSITION)
public class PositionManagerImpl extends AbstractEntityService<PositionEntity, Long, PositionRepository> implements PositionManager {

    /**
     * @see PositionManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_POSITION);
        }
    }

}
