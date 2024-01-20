package cn.elvea.platform.system.dict.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author elvea
 */
@Mapper
public interface DictRelationConverter {

    DictRelationConverter INSTANCE = Mappers.getMapper(DictRelationConverter.class);

}
