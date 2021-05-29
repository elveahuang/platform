package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.DepartmentEntity;
import cn.elvea.platform.core.system.manager.DepartmentManager;
import cn.elvea.platform.core.system.manager.PositionManager;
import cn.elvea.platform.core.system.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * DepartmentManager
 *
 * @author elvea
 * @see DepartmentManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SystemConstants.CACHE_POSITION)
public class DepartmentManagerImpl extends AbstractEntityService<DepartmentEntity, Long, DepartmentRepository> implements DepartmentManager {

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
