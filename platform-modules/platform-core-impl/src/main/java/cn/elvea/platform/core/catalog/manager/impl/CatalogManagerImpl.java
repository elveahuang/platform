package cn.elvea.platform.core.catalog.manager.impl;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.catalog.domain.entity.CatalogEntity;
import cn.elvea.platform.core.catalog.manager.CatalogManager;
import cn.elvea.platform.core.catalog.repository.CatalogRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.core.catalog.CatalogConstants.CACHE_CATALOG;

/**
 * CatalogManager
 *
 * @author elvea
 * @see CatalogManager
 * @since 0.0.1
 */
@Service
@CacheConfig(cacheNames = CACHE_CATALOG)
public class CatalogManagerImpl extends AbstractEntityService<CatalogEntity, Long, CatalogRepository> implements CatalogManager {
}
