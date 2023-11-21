package cn.elvea.platform.system.announcement.web.admin;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.announcement.model.entity.AnnouncementEntity;
import cn.elvea.platform.system.announcement.model.form.AnnouncementForm;
import cn.elvea.platform.system.announcement.model.request.AnnouncementDeleteRequest;
import cn.elvea.platform.system.announcement.model.request.AnnouncementSearchRequest;
import cn.elvea.platform.system.announcement.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "AnnouncementAdminController", description = "公告资讯后台管理控制器")
public class AnnouncementAdminController extends AbstractController {

    private final AnnouncementService announcementService;

    @Operation(summary = "获取公告资讯列表")
    @ApiResponse(description = "获取公告资讯列表")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__LIST)
    @OperationLog("获取公告资讯列表")
    public R<Page<AnnouncementEntity>> list(AnnouncementSearchRequest searchRequest) {
        AnnouncementEntity example = AnnouncementEntity.builder().build();
        example.setActive(Boolean.TRUE);
        return R.success(announcementService.findByPage(searchRequest.getPageable(), example));
    }

    @Operation(summary = "获取公告资讯详情")
    @ApiResponse(description = "获取公告资讯详情")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__DETAILS)
    @OperationLog("获取公告资讯详情")
    public R<AnnouncementEntity> details(@RequestParam Long id) {
        return R.success(announcementService.findById(id));
    }

    @Operation(summary = "准备公告资讯")
    @ApiResponse(description = "准备公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__PREPARE)
    @OperationLog("准备公告资讯")
    public R<AnnouncementEntity> prepare(@RequestParam Long id) {
        return R.success(announcementService.findById(id));
    }

    @Operation(summary = "保存公告资讯")
    @ApiResponse(description = "保存公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__SAVE)
    @OperationLog("保存公告资讯")
    public R<?> save(@Valid AnnouncementForm form) {
        this.announcementService.saveAnnouncement(form);
        return R.success();
    }

    @Operation(summary = "删除公告资讯")
    @ApiResponse(description = "删除公告资讯")
    @PostMapping(API_V1_ADMIN__ANNOUNCEMENT__DELETE)
    @OperationLog("删除公告资讯")
    public R<?> delete(AnnouncementDeleteRequest request) {
        if (request != null && request.getIds() != null && request.getIds().length > 0) {
            announcementService.softDeleteBatchById(Arrays.asList(request.getIds()));
        }
        return R.success();
    }

}
