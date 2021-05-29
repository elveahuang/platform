package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.UserSessionEntity;
import cn.elvea.platform.core.system.manager.UserSessionManager;
import cn.elvea.platform.core.system.repository.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * UserSessionManager
 *
 * @author elvea
 * @see UserSessionManager
 * @since 0.0.1
 */
@Service
@Slf4j
@CacheConfig(cacheNames = SystemConstants.CACHE_USER_SESSION)
public class UserSessionManagerImpl extends AbstractEntityService<UserSessionEntity, Long, UserSessionRepository> implements UserSessionManager {

    /**
     * @see UserSessionManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_USER_SESSION);
        }
    }

}
