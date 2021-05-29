package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.RoleEntity;
import cn.elvea.platform.core.system.manager.RoleManager;
import cn.elvea.platform.core.system.manager.UserManager;
import cn.elvea.platform.core.system.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * RoleManager
 *
 * @author elvea
 * @see RoleManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SystemConstants.CACHE_ROLE)
public class RoleManagerImpl extends AbstractEntityService<RoleEntity, Long, RoleRepository> implements RoleManager {

    /**
     * @see UserManager#findById(Serializable)
     */
    @Override
    @Cacheable(key = "'id-' + #id")
    public RoleEntity findById(Long id) {
        return super.findById(id);
    }

    /**
     * @see RoleManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_ROLE);
        }
    }

}
