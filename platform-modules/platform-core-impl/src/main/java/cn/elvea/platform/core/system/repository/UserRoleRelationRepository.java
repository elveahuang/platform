package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;
import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRoleRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface UserRoleRelationRepository extends JdbcRepository<UserRoleRelationEntity, Long> {

    List<UserRoleRelationEntity> findByUserId(Long userId);

}
