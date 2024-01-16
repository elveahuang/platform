package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 24.1.0
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {
}
