package cn.elvea.platform.system.announcement.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.request.AnnouncementSearchRequest;
import cn.elvea.platform.system.announcement.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__ANNOUNCEMENT__DETAILS;
import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__ANNOUNCEMENT__LIST;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "AnnouncementController", description = "公告资讯控制器")
public class AnnouncementController extends AbstractController {

    private final AnnouncementService announcementService;

    @Operation(summary = "获取公告资讯列表")
    @ApiResponse(description = "获取公告资讯列表")
    @OperationLog("获取公告资讯列表")
    @PostMapping(API_V1__ANNOUNCEMENT__LIST)
    public R<?> list(AnnouncementSearchRequest request) {
        return R.success(announcementService.findByPage(request));
    }

    @Operation(summary = "获取公告资讯详情")
    @ApiResponse(description = "获取公告资讯详情")
    @OperationLog("获取公告资讯详情")
    @PostMapping(API_V1__ANNOUNCEMENT__DETAILS)
    public R<AnnouncementEntity> details(@RequestParam("id") Long id) {
        return R.success(announcementService.findById(id));
    }

}
