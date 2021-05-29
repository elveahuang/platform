package cn.elvea.platform.core.attachment.controller;

import cn.elvea.platform.commons.web.Response;
import cn.elvea.platform.core.attachment.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * AttachmentController
 *
 * @author elvea
 * @since 0.0.1
 */
@RestController
@RequestMapping({"/attachment"})
@Tag(name = "附件", description = "附件")
public class AttachmentController {

    private AttachmentService attachmentService;

    /**
     * 上传文件
     */
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @RequestMapping("/upload")
    @Parameters({
            @Parameter(name = "file", description = "附件", required = true),
    })
    public Response<?> uploadAttachmentFile(@RequestParam(value = "file") MultipartFile file) {
        this.attachmentService.uploadFile();
        return Response.success();
    }

    @Autowired
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

}
