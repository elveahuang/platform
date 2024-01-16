package cn.elvea.platform.system.announcement.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 24.1.0
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {
}
