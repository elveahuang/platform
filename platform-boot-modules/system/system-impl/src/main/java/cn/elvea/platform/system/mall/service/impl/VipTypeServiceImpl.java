package cn.elvea.platform.system.mall.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.enums.ActiveTypeEnum;
import cn.elvea.platform.system.commons.enums.StatusTypeEnum;
import cn.elvea.platform.system.mall.model.entity.VipTypeEntity;
import cn.elvea.platform.system.mall.repository.VipTypeRepository;
import cn.elvea.platform.system.mall.service.VipTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class VipTypeServiceImpl
        extends BaseCachingEntityService<VipTypeEntity, Long, VipTypeRepository>
        implements VipTypeService {

    /**
     * @see VipTypeService#getTypeList()
     */
    @Override
    public List<VipTypeEntity> getTypeList() {
        VipTypeEntity condition = VipTypeEntity.builder().build();
        condition.setStatus(StatusTypeEnum.ENABLED.getValue());
        condition.setActive(ActiveTypeEnum.ACTIVE.getValue());
        Example<VipTypeEntity> example = Example.of(condition);
        return this.repository.findAll(example).stream().toList();
    }

}
