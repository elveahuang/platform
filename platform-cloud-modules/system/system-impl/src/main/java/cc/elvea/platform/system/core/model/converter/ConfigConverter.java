package cc.elvea.platform.system.core.model.converter;

import cc.elvea.platform.system.core.model.dto.ConfigDto;
import cc.elvea.platform.system.core.model.entity.ConfigEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface ConfigConverter {

    ConfigDto entity2Dto(ConfigEntity entity);

}
