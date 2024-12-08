package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.AccountVipEntity;
import cc.elvea.platform.system.mall.model.vo.AccountVipVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface AccountVipConverter {

    AccountVipVo entity2Vo(AccountVipEntity entity);

    List<AccountVipVo> entityList2VoList(List<AccountVipEntity> entityList);

}
