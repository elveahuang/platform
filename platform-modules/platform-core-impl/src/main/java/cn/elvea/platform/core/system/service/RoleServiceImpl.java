package cn.elvea.platform.core.system.service;

import cn.elvea.platform.core.system.domain.dto.RoleDto;
import cn.elvea.platform.core.system.manager.EntityRelationManager;
import cn.elvea.platform.core.system.manager.RoleManager;
import cn.elvea.platform.core.system.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleService
 *
 * @author elvea
 * @see RoleService
 * @since 0.0.1
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleManager roleManager;

    public RoleServiceImpl(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    /**
     * @see RoleService#findRoleByUserId(Long)
     */
    @Override
    public List<RoleDto> findRoleByUserId(Long userId) {
        return null;
    }

}
