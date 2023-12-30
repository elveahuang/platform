package cn.elvea.platform.system.commons.web;

import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.enums.ResponseCodeEnum;
import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.Storage;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.model.AttachmentVo;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static cn.elvea.platform.commons.core.constants.AttachmentConstants.DEFAULT_EDITOR_EXT;
import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__ATTACHMENT__EDITOR_UPLOAD;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "AttachmentController", description = "附件控制器")
public class AttachmentController extends AbstractController {

    private final Storage storage;

    /**
     * 编辑器附件上传
     */
    @Authenticated
    @OperationLog("编辑器附件上传")
    @Operation(summary = "编辑器附件上传")
    @ApiResponse(description = "编辑器附件上传")
    @PostMapping(API_V1__ATTACHMENT__EDITOR_UPLOAD)
    public R<?> uploadEditorAttachment(@RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isNotEmpty(file.getOriginalFilename()) && FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
            FileObject<?> object = storage.getStorageService().uploadFile(file);
            AttachmentVo vo = AttachmentVo.builder().url(object.getUrl()).build();
            return R.success(vo);
        }
        throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
    }

}
