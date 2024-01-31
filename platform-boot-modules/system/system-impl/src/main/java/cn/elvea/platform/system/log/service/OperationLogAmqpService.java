package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.log.domain.OperationLogDto;
import cn.elvea.platform.commons.core.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
