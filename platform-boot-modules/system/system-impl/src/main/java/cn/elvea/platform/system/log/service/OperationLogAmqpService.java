package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.extensions.log.dto.OperationLogDto;
import cn.elvea.platform.commons.core.extensions.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
