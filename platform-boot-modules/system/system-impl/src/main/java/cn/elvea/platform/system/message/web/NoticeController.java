package cn.elvea.platform.system.message.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import cn.elvea.platform.system.message.request.NoticeSearchRequest;
import cn.elvea.platform.system.message.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__DETAILS;
import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__NOTICE__LIST;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "NoticeController", description = "系统通知控制器")
public class NoticeController extends AbstractController {

    private final NoticeService noticeService;

    @Operation(summary = "获取系统通知列表")
    @ApiResponse(description = "获取系统通知列表")
    @PostMapping(API_V1__NOTICE__LIST)
    @OperationLog("获取公告资讯列表")
    public R<?> list(NoticeSearchRequest request) {
        return R.success(noticeService.findByUserId(request));
    }

    @Operation(summary = "获取系统通知详情")
    @ApiResponse(description = "获取系统通知详情")
    @PostMapping(API_V1__NOTICE__DETAILS)
    @OperationLog("获取公告资讯详情")
    public R<NoticeEntity> details(@RequestParam Long id) {
        return R.success(noticeService.findById(id));
    }

}
