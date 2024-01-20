package cn.elvea.platform.system.tag.web;

import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.tag.api.TagApi;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.API_V1__TAG__TYPE;

/**
 * @author elvea
 * @since 24.1.0
 */
@RestController
@AllArgsConstructor
@Tag(name = "TagController", description = "标签控制器")
public class TagController extends AbstractController {

    private final TagApi tagApi;

    @Authenticated
    @Operation(summary = "获取标签类型")
    @ApiResponse(description = "获取标签类型")
    @OperationLog("获取标签类型")
    @PostMapping(API_V1__TAG__TYPE)
    public R<TagTypeVo> type(@RequestParam("code") String code) {
        return R.success(tagApi.getTagType(code));
    }

}
