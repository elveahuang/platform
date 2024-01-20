package cn.elvea.platform.system.tag.web.admin;

import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "TagTypeAdminController", description = "标签后台管理控制器")
public class TagTypeAdminController extends AbstractController {
}
