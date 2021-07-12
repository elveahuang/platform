package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.core.system.domain.entity.TenantAuthorityRelationEntity;
import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TenantAuthorityRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface TenantAuthorityRelationRepository extends JdbcRepository<TenantAuthorityRelationEntity, Long> {

    List<TenantAuthorityRelationEntity> findByTenantId(Long tenantId);

    void deleteByTenantId(Long tenantId);

    int countByTenantId(Long tenantId);

}
