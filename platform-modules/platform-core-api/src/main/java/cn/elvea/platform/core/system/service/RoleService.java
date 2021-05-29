package cn.elvea.platform.core.system.service;

import cn.elvea.platform.core.system.domain.dto.RoleDto;

import java.util.List;

/**
 * RoleService
 *
 * @author elvea
 * @since 0.0.1
 */
public interface RoleService {

    /**
     * 根据用户ID获取用户所有角色
     *
     * @param userId 用户ID
     * @return 用户所有角色
     */
    List<RoleDto> findRoleByUserId(Long userId);

}
