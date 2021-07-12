package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;

import java.util.List;

/**
 * UserRoleRelationManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface UserRoleRelationManager extends EntityService<UserRoleRelationEntity, Long> {
    /**
     * 根据用户ID查询所有的角色关联
     *
     * @param userId 用户ID
     * @return 角色关联
     */
    List<UserRoleRelationEntity> findByUserId(Long userId);
}
