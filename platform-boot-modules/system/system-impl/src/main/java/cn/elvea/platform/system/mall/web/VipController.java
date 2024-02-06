package cn.elvea.platform.system.mall.web;

import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.mall.api.VipApi;
import cn.elvea.platform.system.mall.model.vo.VipTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__VIP__TYPE;

/**
 * @author dev
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "VipController", description = "会员控制器")
public class VipController extends AbstractController {

    private final VipApi vipApi;

    @Authenticated
    @Operation(summary = "获取当前会员类型")
    @ApiResponse(description = "获取当前会员类型")
    @OperationLog("获取当前会员类型")
    @PostMapping(API_V1__VIP__TYPE)
    public R<List<VipTypeVo>> typeList() {
        return R.success(this.vipApi.getTypeList());
    }

}
