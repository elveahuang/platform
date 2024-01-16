package cn.elvea.platform.system.catalog.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.catalog.model.entity.CatalogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface CatalogRepository extends BaseEntityRepository<CatalogEntity, Long> {
}
