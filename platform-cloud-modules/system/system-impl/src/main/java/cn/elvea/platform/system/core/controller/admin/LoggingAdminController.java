package cn.elvea.platform.system.core.controller.admin;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 *
 * @author elvea
 * @since 24.1.0
 */
@RestController
@Tag(name = "LoggingMgrController", description = "日志管理控制器")
public class LoggingAdminController extends AbstractController {
}
