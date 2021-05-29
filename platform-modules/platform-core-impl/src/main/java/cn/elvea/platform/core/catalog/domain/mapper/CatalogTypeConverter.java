package cn.elvea.platform.core.catalog.domain.mapper;

import cn.elvea.platform.core.catalog.domain.entity.CatalogTypeEntity;
import cn.elvea.platform.core.catalog.dto.CatalogTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 */
@Mapper
public interface CatalogTypeConverter {

    CatalogTypeConverter INSTANCE = Mappers.getMapper(CatalogTypeConverter.class);

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}
