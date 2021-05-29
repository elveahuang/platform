package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.AuthorityEntity;
import cn.elvea.platform.core.system.manager.AuthorityManager;
import cn.elvea.platform.core.system.repository.AuthorityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * AuthorityManager
 *
 * @author elvea
 * @see AuthorityManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SystemConstants.CACHE_AUTHORITY)
public class AuthorityManagerImpl extends AbstractEntityService<AuthorityEntity, Long, AuthorityRepository> implements AuthorityManager {

    /**
     * @see AuthorityManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_AUTHORITY);
        }
    }

}
