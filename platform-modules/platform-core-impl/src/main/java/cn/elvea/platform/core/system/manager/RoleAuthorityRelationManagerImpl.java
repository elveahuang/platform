package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.core.system.domain.entity.RoleAuthorityRelationEntity;
import cn.elvea.platform.core.system.repository.RoleAuthorityRelationRepository;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.elvea.platform.core.system.SystemConstants.CACHE_TENANT_AUTHORITY_RELATION;

/**
 * RoleAuthorityRelationManager
 *
 * @author elvea
 * @see RoleAuthorityRelationManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = CACHE_TENANT_AUTHORITY_RELATION)
public class RoleAuthorityRelationManagerImpl
        extends AbstractEntityService<RoleAuthorityRelationEntity, Long, RoleAuthorityRelationRepository>
        implements RoleAuthorityRelationManager {

    /**
     * @see RoleAuthorityRelationManager#findByRoleId(Long)
     */
    @Override
    @Cacheable(key = "#roleId")
    public List<RoleAuthorityRelationEntity> findByRoleId(Long roleId) {
        return this.repository.findByRoleId(roleId);
    }

}
