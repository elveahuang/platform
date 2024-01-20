package cn.elvea.platform.system.dict.model.converter;

import cn.elvea.platform.system.dict.model.entity.DictItemEntity;
import cn.elvea.platform.system.dict.model.form.DictForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface DictConverter {

    DictConverter INSTANCE = Mappers.getMapper(DictConverter.class);

    @Mapping(target = "idx", ignore = true)
    @Mapping(target = "source", ignore = true)
    DictItemEntity form2Entity(DictForm form);

}
