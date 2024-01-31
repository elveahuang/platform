package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.core.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaLogAmqpService extends AmqpService<CaptchaLogDto> {
}
