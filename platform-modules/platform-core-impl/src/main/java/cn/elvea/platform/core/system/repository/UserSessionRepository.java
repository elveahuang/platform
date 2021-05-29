package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.UserSessionEntity;
import org.springframework.stereotype.Repository;

/**
 * UserSessionRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface UserSessionRepository extends JdbcRepository<UserSessionEntity, Long> {
}
