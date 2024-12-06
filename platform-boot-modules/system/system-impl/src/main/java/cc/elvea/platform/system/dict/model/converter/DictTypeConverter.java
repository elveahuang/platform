package cc.elvea.platform.system.dict.model.converter;

import cc.elvea.platform.system.dict.model.entity.DictTypeEntity;
import cc.elvea.platform.system.dict.model.vo.DictTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface DictTypeConverter {

    @Mapping(target = "items", ignore = true)
    DictTypeVo entity2Vo(DictTypeEntity entity);

}
