package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.DepartmentEntity;

/**
 * DepartmentManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface DepartmentManager extends EntityService<DepartmentEntity, Long> {
    /**
     * 清空缓存
     */
    void clearCache();
}
