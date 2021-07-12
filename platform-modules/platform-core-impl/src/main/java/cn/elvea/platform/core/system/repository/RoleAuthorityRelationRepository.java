package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.core.system.domain.entity.RoleAuthorityRelationEntity;
import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleAuthorityRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface RoleAuthorityRelationRepository extends JdbcRepository<RoleAuthorityRelationEntity, Long> {

    List<RoleAuthorityRelationEntity> findByRoleId(Long roleId);

}
