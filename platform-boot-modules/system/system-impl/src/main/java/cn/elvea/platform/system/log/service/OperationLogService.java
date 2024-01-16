package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface OperationLogService extends CachingEntityService<OperationLogEntity, Long> {
}
