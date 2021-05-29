package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.AuthorityEntity;
import org.springframework.stereotype.Repository;

/**
 * AuthorityRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface AuthorityRepository extends JdbcRepository<AuthorityEntity, Long> {
}
