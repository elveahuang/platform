package cn.elvea.platform.system.mall.api.impl;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.mall.api.PayApi;
import cn.elvea.platform.system.mall.model.converter.PayTypeConverter;
import cn.elvea.platform.system.mall.model.entity.PayTypeEntity;
import cn.elvea.platform.system.mall.model.vo.PayTypeVo;
import cn.elvea.platform.system.mall.service.PayTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class PayApiImpl implements PayApi {

    private PayTypeService payTypeService;

    @Override
    public List<PayTypeVo> getPayTypeList() {
        List<PayTypeEntity> typeEntityList = this.payTypeService.getTypeList();
        if (CollectionUtils.isNotEmpty(typeEntityList)) {
            return PayTypeConverter.INSTANCE.entityList2VoList(typeEntityList);
        }
        return Collections.emptyList();
    }

}
