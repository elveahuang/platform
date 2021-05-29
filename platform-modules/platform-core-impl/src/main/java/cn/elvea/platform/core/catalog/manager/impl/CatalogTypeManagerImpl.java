package cn.elvea.platform.core.catalog.manager.impl;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.catalog.domain.entity.CatalogTypeEntity;
import cn.elvea.platform.core.catalog.manager.CatalogTypeManager;
import cn.elvea.platform.core.catalog.repository.CatalogTypeRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.core.catalog.CatalogConstants.CACHE_CATALOG_TYPE;

/**
 * CatalogTypeManager
 *
 * @author elvea
 * @see CatalogTypeManager
 * @since 0.0.1
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG_TYPE)
public class CatalogTypeManagerImpl extends AbstractEntityService<CatalogTypeEntity, Long, CatalogTypeRepository> implements CatalogTypeManager {
}
