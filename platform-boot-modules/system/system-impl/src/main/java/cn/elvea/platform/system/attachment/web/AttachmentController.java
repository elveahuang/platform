package cn.elvea.platform.system.attachment.web;

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
import cn.elvea.platform.system.attachment.api.AttachmentApi;
import cn.elvea.platform.system.attachment.model.AttachmentFile;
import cn.elvea.platform.system.attachment.model.AttachmentParameter;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

import static cn.elvea.platform.commons.core.constants.AttachmentConstants.DEFAULT_EDITOR_EXT;
import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "AttachmentController", description = "附件控制器")
public class AttachmentController extends AbstractController {

    private final Storage storage;

    private final AttachmentApi attachmentApi;

    /**
     * 附件上传
     */
    @Authenticated
    @OperationLog("附件上传")
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @PostMapping(API_V1__ATTACHMENT__UPLOAD_FILE)
    public R<?> uploadAttachmentFile(@RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isNotEmpty(file.getOriginalFilename()) && FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
            FileObject<?> object = storage.getStorageService().uploadFile(file);
            AttachmentVo vo = AttachmentVo.builder().url(object.getUrl()).build();
            return R.success(vo);
        }
        throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
    }

    /**
     * 附件上传
     */
    @Authenticated
    @OperationLog("附件上传")
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @PostMapping(API_V1__ATTACHMENT__UPLOAD)
    public R<?> uploadAttachment(AttachmentParameter parameter, MultipartHttpServletRequest request) throws Exception {
        // 获取待上传文件集合
        List<MultipartFile> files = Lists.newArrayList();
        Iterator<String> it = request.getFileNames();
        while (it.hasNext()) {
            files.add(request.getFile(it.next()));
        }

        // 验证所有文件的文件名
        for (MultipartFile file : Lists.newArrayList(files)) {
            if (StringUtils.isEmpty(file.getOriginalFilename()) || !FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
                throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
            }
        }

        List<AttachmentFile> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                result.add(this.attachmentApi.uploadAttachmentFile(parameter, file));
            }
        }
        return R.success(result);
    }

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
