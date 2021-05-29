package cn.elvea.platform.core.system.controller;

import cn.elvea.platform.base.annotations.OptLog;
import cn.elvea.platform.base.constants.GlobalConstants;
import cn.elvea.platform.commons.web.Response;
import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * UserController
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "用户控制器")
public class UserController {

    /**
     * 获取用户登录信息
     *
     * @return 用户登录信息
     */
    @Operation(summary = "获取用户登录信息")
    @ApiResponse(description = "获取用户登录信息")
    @GetMapping
    @OptLog("获取用户登录信息")
    public Response<?> version() {
        Map<String, Object> data = Maps.newLinkedHashMap();
        data.put("version", GlobalConstants.VERSION);
        data.put("now", LocalDateTime.now());
        return Response.success(data);
    }

}
