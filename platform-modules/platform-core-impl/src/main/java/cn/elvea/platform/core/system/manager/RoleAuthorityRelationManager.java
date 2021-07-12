package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.RoleAuthorityRelationEntity;

import java.util.List;

/**
 * RoleAuthorityRelationManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface RoleAuthorityRelationManager extends EntityService<RoleAuthorityRelationEntity, Long> {

    List<RoleAuthorityRelationEntity> findByRoleId(Long roleId);

}
