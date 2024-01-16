package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.extensions.amqp.AmqpService;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserSessionAmqpService extends AmqpService<UserSessionDto> {
}
