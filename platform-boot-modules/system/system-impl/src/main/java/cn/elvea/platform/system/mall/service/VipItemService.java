package cn.elvea.platform.system.mall.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.mall.model.entity.VipItemEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface VipItemService extends CachingEntityService<VipItemEntity, Long> {

    /**
     * 获取指定会员类型下属的套餐信息
     */
    List<VipItemEntity> getVipItemByVipType(Long vipTypeId);

}
