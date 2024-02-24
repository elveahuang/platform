package cn.elvea.platform.system.mall.service.impl;

import cn.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.mall.model.entity.OrderPayEntity;
import cn.elvea.platform.system.mall.repository.OrderPayRepository;
import cn.elvea.platform.system.mall.service.OrderPayService;
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
public class OrderPayServiceImpl
        extends BaseCachingEntityService<OrderPayEntity, Long, OrderPayRepository>
        implements OrderPayService {
}
