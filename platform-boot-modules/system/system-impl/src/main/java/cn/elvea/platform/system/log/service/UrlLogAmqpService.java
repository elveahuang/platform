package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.logging.domain.UrlLogDto;
import cn.elvea.platform.commons.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
