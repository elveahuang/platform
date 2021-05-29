package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.RoleAuthorityRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * RoleAuthorityRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface RoleAuthorityRelationRepository extends JdbcRepository<RoleAuthorityRelationEntity, Long> {
}
