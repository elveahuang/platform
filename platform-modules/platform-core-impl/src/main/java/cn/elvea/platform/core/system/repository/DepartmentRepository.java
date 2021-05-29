package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.DepartmentEntity;
import org.springframework.stereotype.Repository;

/**
 * DepartmentRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface DepartmentRepository extends JdbcRepository<DepartmentEntity, Long> {
}
