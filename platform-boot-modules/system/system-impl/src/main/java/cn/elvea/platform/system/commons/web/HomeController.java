package cn.elvea.platform.system.commons.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.extensions.platform.Platform;
import cn.elvea.platform.commons.core.extensions.platform.PlatformHelper;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author elvea
 * @since 0.0.1
 */
@Controller
@RequestMapping("/")
@Tag(name = "HomeController", description = "首页控制器")
public class HomeController extends AbstractController {

    @Operation(summary = "首页")
    @ApiResponse(description = "首页")
    @GetMapping("/")
    @OperationLog("首页")
    public String index() {
        String page = "";
        if (this.context.isProductionMode()) {
            Platform platform = PlatformHelper.fromServletRequest();
            if (platform.isMobile()) {
                page = StringUtils.isNotEmpty(this.context.getHome().getMobile()) ? this.context.getHome().getMobile() : "";
            } else {
                page = StringUtils.isNotEmpty(this.context.getHome().getWebapp()) ? this.context.getHome().getWebapp() : "";
            }
        }
        if (StringUtils.isEmpty(page)) {
            page = StringUtils.isNotEmpty(this.context.getHome().getMain()) ? this.context.getHome().getMain() : "about";
        }
        return redirect(page);
    }

    @Operation(summary = "关于页")
    @ApiResponse(description = "关于页")
    @GetMapping("/about")
    @OperationLog("关于页")
    public String about() {
        return "about";
    }

}
