package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.message.amqp.AmqpService;
import cn.elvea.platform.commons.core.log.domain.UrlLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
