package cn.elvea.platform.system.mall.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.mall.model.entity.OrderLogEntity;
import cn.elvea.platform.system.mall.repository.OrderLogRepository;
import cn.elvea.platform.system.mall.service.OrderLogService;
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
public class OrderLogServiceImpl
        extends BaseCachingEntityService<OrderLogEntity, Long, OrderLogRepository>
        implements OrderLogService {
}
