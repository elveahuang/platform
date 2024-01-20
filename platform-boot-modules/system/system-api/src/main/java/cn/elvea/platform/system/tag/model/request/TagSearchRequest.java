package cn.elvea.platform.system.tag.model.request;

import cn.elvea.platform.commons.core.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagSearchRequest extends PageRequest {
    /**
     * 类型ID
     */
    @Builder.Default
    @Schema(title = "标签类型ID", defaultValue = "0")
    private Long typeId = 0L;
    /**
     * 类型编号
     */
    @Schema(title = "标签类型编号")
    private String type;
}
