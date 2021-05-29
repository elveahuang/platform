package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * UserRoleRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface UserRoleRelationRepository extends JdbcRepository<UserRoleRelationEntity, Long> {
}
