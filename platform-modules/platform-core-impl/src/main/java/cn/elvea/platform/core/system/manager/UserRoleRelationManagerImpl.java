package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;
import cn.elvea.platform.core.system.repository.UserRoleRelationRepository;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.elvea.platform.core.system.SystemConstants.CACHE_USER_ROLE_RELATION;

/**
 * UserRoleRelationManager
 *
 * @author elvea
 * @see UserRoleRelationManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = CACHE_USER_ROLE_RELATION)
public class UserRoleRelationManagerImpl
        extends AbstractEntityService<UserRoleRelationEntity, Long, UserRoleRelationRepository>
        implements UserRoleRelationManager {

    /**
     * @see UserRoleRelationManager#findByUserId(Long)
     */
    @Override
    @Cacheable(key = "#userId")
    public List<UserRoleRelationEntity> findByUserId(Long userId) {
        return this.repository.findByUserId(userId);
    }

}
