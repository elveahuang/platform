package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.UserSessionEntity;

/**
 * UserSessionManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface UserSessionManager extends EntityService<UserSessionEntity, Long> {
    /**
     * 清空缓存
     */
    void clearCache();
}
