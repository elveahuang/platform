package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.core.system.domain.entity.TenantAuthorityRelationEntity;
import cn.elvea.platform.core.system.repository.TenantAuthorityRelationRepository;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.elvea.platform.core.system.SystemConstants.CACHE_ROLE_AUTHORITY_RELATION;

/**
 * TenantAuthorityManager
 *
 * @author elvea
 * @see TenantAuthorityRelationManager
 * @since 0.0.1
 */
@Service
@Slf4j
@CacheConfig(cacheNames = CACHE_ROLE_AUTHORITY_RELATION)
public class TenantAuthorityRelationManagerImpl
        extends AbstractEntityService<TenantAuthorityRelationEntity, Long, TenantAuthorityRelationRepository>
        implements TenantAuthorityRelationManager {

    /**
     * @see TenantAuthorityRelationManager#findByTenantId(Long)
     */
    @Override
    @Cacheable(key = "#tenantId")
    public List<TenantAuthorityRelationEntity> findByTenantId(Long tenantId) {
        return this.repository.findByTenantId(tenantId);
    }

    /**
     * @see TenantAuthorityRelationManager#updateByTenantId(Long, List)
     */
    @Override
    @CachePut(key = "#tenantId")
    public List<TenantAuthorityRelationEntity> updateByTenantId(Long tenantId, List<Long> authorityIdList) {
        if (this.repository.countByTenantId(tenantId) > 0) {
            this.repository.deleteByTenantId(tenantId);
        }

        List<TenantAuthorityRelationEntity> entityList = new ArrayList<>();
        for (Long authorityId : authorityIdList) {
            TenantAuthorityRelationEntity entity = new TenantAuthorityRelationEntity();
            entity.setTenantId(tenantId);
            entity.setAuthorityId(authorityId);
            entityList.add(entity);
        }
        this.repository.saveAll(entityList);

        return this.repository.findByTenantId(tenantId);
    }

    /**
     * @see TenantAuthorityRelationManager#deleteByTenantId(Long)
     */
    @Override
    @CacheEvict(key = "#tenantId")
    public void deleteByTenantId(Long tenantId) {
        if (this.repository.countByTenantId(tenantId) > 0) {
            this.repository.deleteByTenantId(tenantId);
        }
    }

    /**
     * @see TenantAuthorityRelationManager#clearCache()
     */
    @Override
    @CacheEvict(allEntries = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", CACHE_ROLE_AUTHORITY_RELATION);
        }
    }

    /**
     * @see TenantAuthorityRelationManager#clearCache(Long)
     */
    @Override
    @CacheEvict(key = "#tenantId")
    public void clearCache(Long tenantId) {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}] by key [{}]...", CACHE_ROLE_AUTHORITY_RELATION, tenantId);
        }
    }

}
