package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.TenantAuthorityRelationEntity;

import java.util.List;

/**
 * TenantAuthorityRelationManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface TenantAuthorityRelationManager extends EntityService<TenantAuthorityRelationEntity, Long> {

    List<TenantAuthorityRelationEntity> findByTenantId(Long tenantId);

    List<TenantAuthorityRelationEntity> updateByTenantId(Long tenantId, List<Long> authorityIdList);

    void deleteByTenantId(Long tenantId);

    void clearCache();

    void clearCache(Long tenantId);

}
