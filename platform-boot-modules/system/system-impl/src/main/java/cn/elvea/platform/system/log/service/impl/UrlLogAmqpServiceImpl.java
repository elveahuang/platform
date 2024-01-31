package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.core.logging.domain.UrlLogDto;
import cn.elvea.platform.commons.core.message.amqp.AbstractAmqpService;
import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cn.elvea.platform.system.log.model.converter.UrlLogConverter;
import cn.elvea.platform.system.log.model.entity.UrlLogEntity;
import cn.elvea.platform.system.log.service.UrlLogAmqpService;
import cn.elvea.platform.system.log.service.UrlLogService;
import cn.elvea.platform.system.log.service.UrlStatLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UrlLogAmqpService
 * @see AbstractAmqpService
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
@RabbitListener(queues = SystemAmqpConstants.URL_LOG_QUEUE)
public class UrlLogAmqpServiceImpl extends AbstractAmqpService<UrlLogDto> implements UrlLogAmqpService {

    private final UrlLogService urlLogService;

    private final UrlStatLogService urlStatLogService;

    @Override
    public void execute(UrlLogDto dto) {
        UrlLogEntity entity = UrlLogConverter.INSTANCE.dto2Entity(dto);
        this.urlLogService.save(entity);
        this.urlStatLogService.saveUrlStatLog(dto);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.URL_LOG_QUEUE;
    }

}
