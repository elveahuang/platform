package cn.elvea.platform.system.tag.model.converter;

import cn.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 */
@Mapper
public interface TagTypeConverter {

    TagTypeConverter INSTANCE = Mappers.getMapper(TagTypeConverter.class);

    @Mapping(target = "items", ignore = true)
    TagTypeVo entity2Vo(TagTypeEntity entity);

}
