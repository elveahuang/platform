package cn.elvea.platform.system.mall.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.mall.model.entity.PayTypeEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface PayTypeService extends CachingEntityService<PayTypeEntity, Long> {

    /**
     * 获取当前可用支付方式
     */
    List<PayTypeEntity> getTypeList();

}
