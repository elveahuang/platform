package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.RoleEntity;

/**
 * RoleManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface RoleManager extends EntityService<RoleEntity, Long> {

    /**
     * 清空缓存
     */
    void clearCache();

}
