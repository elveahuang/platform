package cn.elvea.platform.core.catalog.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.catalog.domain.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

/**
 * CatalogRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface CatalogRepository extends JdbcRepository<CatalogEntity, Long> {
}
