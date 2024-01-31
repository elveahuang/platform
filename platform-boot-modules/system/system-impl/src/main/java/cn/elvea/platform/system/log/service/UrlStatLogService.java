package cn.elvea.platform.system.log.service;

import cn.elvea.platform.commons.core.logging.domain.UrlLogDto;
import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.log.model.entity.UrlStatLogEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UrlStatLogService extends CachingEntityService<UrlStatLogEntity, Long> {

    UrlStatLogEntity findUrlStatLogByPath(String path);

    void saveUrlStatLog(UrlLogDto urlLog);

}
