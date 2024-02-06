package cn.elvea.platform.system.mall.api.impl;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.system.mall.api.VipApi;
import cn.elvea.platform.system.mall.model.converter.AccountVipConverter;
import cn.elvea.platform.system.mall.model.converter.VipItemConverter;
import cn.elvea.platform.system.mall.model.converter.VipTypeConverter;
import cn.elvea.platform.system.mall.model.entity.AccountVipEntity;
import cn.elvea.platform.system.mall.model.entity.VipItemEntity;
import cn.elvea.platform.system.mall.model.entity.VipTypeEntity;
import cn.elvea.platform.system.mall.model.vo.VipTypeVo;
import cn.elvea.platform.system.mall.service.AccountVipService;
import cn.elvea.platform.system.mall.service.VipItemService;
import cn.elvea.platform.system.mall.service.VipTypeService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author dev
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class VipApiImpl implements VipApi {

    private final VipTypeService vipTypeService;

    private final VipItemService vipItemService;

    private final AccountVipService accountVipService;

    @Override
    public List<VipTypeVo> getTypeList() {
        List<VipTypeVo> vipTypeList = Lists.newArrayList();
        // 获取会员类型
        List<VipTypeEntity> vipTypeEntityList = this.vipTypeService.getTypeList();
        if (CollectionUtils.isNotEmpty(vipTypeEntityList)) {
            vipTypeList.addAll(VipTypeConverter.INSTANCE.entityList2VoList(vipTypeEntityList));
        }

        // 获取会员类型套餐信息
        if (CollectionUtils.isNotEmpty(vipTypeList)) {
            List<VipItemEntity> vipItemEntityList = this.vipItemService.getVipItemByVipType(0L);
            vipTypeList.forEach((vipType -> vipType.setItems(vipItemEntityList.stream()
                    .filter((e) -> e.getVipTypeId().longValue() == vipType.getId().longValue())
                    .map(VipItemConverter.INSTANCE::entity2Vo)
                    .toList())));
        }

        // 获取账号会员信息
        if (CollectionUtils.isNotEmpty(vipTypeList) && SecurityUtils.isAuthenticated()) {
            List<AccountVipEntity> accountVipEntityList = this.accountVipService.getAccountVip(SecurityUtils.getUid());
            if (CollectionUtils.isNotEmpty(accountVipEntityList)) {
                vipTypeList.forEach((vipType -> {
                    Optional<AccountVipEntity> result = accountVipEntityList.stream().filter((e) -> e.getVipTypeId().longValue() == vipType.getId().longValue()).findFirst();
                    result.ifPresent(entity -> vipType.setAccountVip(AccountVipConverter.INSTANCE.entity2Vo(entity)));
                }));
            }
        }
        return vipTypeList;
    }

}
