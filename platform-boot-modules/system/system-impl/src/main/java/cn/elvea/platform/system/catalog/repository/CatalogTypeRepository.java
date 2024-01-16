package cn.elvea.platform.system.catalog.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface CatalogTypeRepository extends BaseEntityRepository<CatalogTypeEntity, Long> {
}
