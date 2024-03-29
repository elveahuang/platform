package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.message.amqp.AbstractAmqpService;
import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cn.elvea.platform.system.log.model.converter.CaptchaLogConverter;
import cn.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import cn.elvea.platform.system.log.service.CaptchaLogAmqpService;
import cn.elvea.platform.system.log.service.CaptchaLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see CaptchaLogService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.CAPTCHA_LOG_QUEUE)
public class CaptchaLogAmqpServiceImpl extends AbstractAmqpService<CaptchaLogDto> implements CaptchaLogAmqpService {

    private final CaptchaLogService captchaLogService;

    public CaptchaLogAmqpServiceImpl(CaptchaLogService captchaLogService) {
        this.captchaLogService = captchaLogService;
    }

    @Override
    public void execute(CaptchaLogDto dto) {
        CaptchaLogEntity entity = CaptchaLogConverter.INSTANCE.dto2Entity(dto);
        this.captchaLogService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.CAPTCHA_LOG_QUEUE;
    }

}
