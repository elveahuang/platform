package cn.elvea.platform.system.banner.web.app;

import cn.elvea.platform.commons.annotations.Anonymous;
import cn.elvea.platform.commons.annotations.OperationLog;
import cn.elvea.platform.commons.web.R;
import cn.elvea.platform.commons.web.controller.AbstractController;
import cn.elvea.platform.system.banner.model.entity.BannerEntity;
import cn.elvea.platform.system.banner.model.request.BannerSearchRequest;
import cn.elvea.platform.system.banner.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__BANNER__LIST;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "BannerController", description = "宣传栏控制器")
public class BannerController extends AbstractController {

    private final BannerService bannerService;

    @Anonymous
    @Operation(summary = "获取宣传栏列表")
    @ApiResponse(description = "获取宣传栏列表")
    @PostMapping(API_V1__BANNER__LIST)
    @OperationLog("获取宣传栏列表")
    public R<Page<BannerEntity>> list(BannerSearchRequest request) {
        return R.success(bannerService.findBannerForUser(request));
    }

}
