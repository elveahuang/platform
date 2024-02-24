package cn.elvea.platform.system.mall.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.mall.model.entity.AccountVipEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AccountVipService extends CachingEntityService<AccountVipEntity, Long> {

    /**
     * 获取账号会员信息
     */
    List<AccountVipEntity> getAccountVip(Long accountId);

}
