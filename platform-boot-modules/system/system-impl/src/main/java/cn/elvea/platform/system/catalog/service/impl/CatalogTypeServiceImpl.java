package cn.elvea.platform.system.catalog.service.impl;

import cn.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import cn.elvea.platform.system.catalog.repository.CatalogTypeRepository;
import cn.elvea.platform.system.catalog.service.CatalogTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
public class CatalogTypeServiceImpl extends BaseCachingEntityService<CatalogTypeEntity, Long, CatalogTypeRepository> implements CatalogTypeService {
}
