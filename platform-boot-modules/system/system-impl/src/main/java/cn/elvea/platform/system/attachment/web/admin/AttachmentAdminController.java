package cn.elvea.platform.system.attachment.web.admin;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "AttachmentAdminController", description = "附件后台管理控制器")
public class AttachmentAdminController extends AbstractController {
}
