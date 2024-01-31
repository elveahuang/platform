package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.log.model.entity.UrlLogEntity;
import cn.elvea.platform.system.log.repository.UrlLogRepository;
import cn.elvea.platform.system.log.service.UrlLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UrlLogService
 * @see BaseEntityService
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class UrlLogServiceImpl
        extends BaseEntityService<UrlLogEntity, Long, UrlLogRepository>
        implements UrlLogService {
}
