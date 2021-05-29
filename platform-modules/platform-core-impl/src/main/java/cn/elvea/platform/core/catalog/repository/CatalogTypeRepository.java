package cn.elvea.platform.core.catalog.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.catalog.domain.entity.CatalogTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * CatalogTypeRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface CatalogTypeRepository extends JdbcRepository<CatalogTypeEntity, Long> {
}
