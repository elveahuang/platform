package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.TenantEntity;
import org.springframework.stereotype.Repository;

/**
 * TenantRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface TenantRepository extends JdbcRepository<TenantEntity, Long> {
}
