package cc.elvea.platform.system.catalog.model.converter;

import cc.elvea.platform.system.catalog.model.dto.CatalogTypeDto;
import cc.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface CatalogTypeConverter {

    CatalogTypeDto entityToDto(CatalogTypeEntity entity);

}
