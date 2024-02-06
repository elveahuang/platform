package cn.elvea.platform.system.mall.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.mall.model.entity.OrderTypeEntity;
import cn.elvea.platform.system.mall.repository.OrderTypeRepository;
import cn.elvea.platform.system.mall.service.OrderTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class OrderTypeServiceImpl
        extends BaseCachingEntityService<OrderTypeEntity, Long, OrderTypeRepository>
        implements OrderTypeService {
}
