package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface RoleRepository extends JdbcRepository<RoleEntity, Long> {
}
