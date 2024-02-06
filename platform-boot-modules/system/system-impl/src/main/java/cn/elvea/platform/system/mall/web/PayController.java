package cn.elvea.platform.system.mall.web;

import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.mall.api.PayApi;
import cn.elvea.platform.system.mall.model.vo.PayTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__PAY__TYPE;

/**
 * @author dev
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "PayController", description = "支付控制器")
public class PayController extends AbstractController {

    private final PayApi payApi;

    @Authenticated
    @Operation(summary = "获取当前可用支付方式")
    @ApiResponse(description = "获取当前可用支付方式")
    @OperationLog("获取当前可用支付方式")
    @PostMapping(API_V1__PAY__TYPE)
    public R<List<PayTypeVo>> payTypeList() {
        return R.success(this.payApi.getPayTypeList());
    }

}
