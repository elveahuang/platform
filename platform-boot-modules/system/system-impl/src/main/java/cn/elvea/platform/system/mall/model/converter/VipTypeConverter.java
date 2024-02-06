package cn.elvea.platform.system.mall.model.converter;

import cn.elvea.platform.system.mall.model.entity.VipTypeEntity;
import cn.elvea.platform.system.mall.model.vo.VipTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface VipTypeConverter {

    VipTypeConverter INSTANCE = Mappers.getMapper(VipTypeConverter.class);

    List<VipTypeVo> entityList2VoList(List<VipTypeEntity> entityList);

}
