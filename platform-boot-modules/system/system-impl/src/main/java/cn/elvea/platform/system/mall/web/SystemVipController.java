package cn.elvea.platform.system.mall.web;

import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1_ADMIN__VIP__LIST;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "SystemVipController", description = "会员后台控制器")
public class SystemVipController extends AbstractController {

    @Authenticated
    @Operation(summary = "会员类型列表")
    @ApiResponse(description = "会员类型列表")
    @OperationLog("会员类型列表")
    @PostMapping(API_V1_ADMIN__VIP__LIST)
    public R<?> list() {
        return R.success();
    }

}
