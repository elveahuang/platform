package cn.elvea.platform.core.system.controller;

import cn.elvea.platform.base.annotations.OptLog;
import cn.elvea.platform.base.constants.GlobalConstants;
import cn.elvea.platform.commons.web.Response;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * DefaultController
 *
 * @author elvea
 * @since 0.0.1
 */
@RestController
@Tag(name = "Default", description = "默认控制器")
public class DefaultController {

    /**
     * 应用初始化
     *
     * @return 应用版本号
     */
    @Operation(summary = "应用初始化")
    @ApiResponse(description = "应用初始化")
    @GetMapping("/api/initialize")
    @OptLog("应用初始化")
    public Response<?> initialize() {
        Map<String, Object> data = Maps.newLinkedHashMap();
        data.put("version", GlobalConstants.VERSION);
        data.put("now", LocalDateTime.now());
        return Response.success(data);
    }

    /**
     * 获取当前应用版本
     *
     * @return 应用版本号
     */
    @Operation(summary = "默认控制器")
    @ApiResponse(description = "当前应用版本号")
    @GetMapping("/api/version")
    @OptLog("获取当前应用版本")
    public Response<?> version() {
        Map<String, Object> data = Maps.newLinkedHashMap();
        data.put("version", GlobalConstants.VERSION);
        data.put("now", LocalDateTime.now());
        return Response.success(data);
    }

}
