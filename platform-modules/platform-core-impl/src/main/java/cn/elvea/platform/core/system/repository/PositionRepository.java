package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.PositionEntity;
import org.springframework.stereotype.Repository;

/**
 * PositionRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface PositionRepository extends JdbcRepository<PositionEntity, Long> {
}
