package cn.elvea.platform.core.system.controller;

import cn.elvea.platform.commons.web.Response;
import cn.elvea.platform.core.system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/role-type")
@Tag(name = "Role", description = "角色控制器")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 查询单个角色类型
     *
     * @return 查询结果
     */
    @Operation()
    @PostMapping(value = "/find-by-code")
    public Response<?> getById(@RequestBody String code) {
        return Response.success();
    }

}
