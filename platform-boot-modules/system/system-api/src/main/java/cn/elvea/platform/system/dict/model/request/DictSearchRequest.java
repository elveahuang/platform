package cn.elvea.platform.system.dict.model.request;

import cn.elvea.platform.commons.core.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DictSearchRequest extends PageRequest {
    /**
     * id
     */
    @Builder.Default
    @Schema(title = "标签类型ID", defaultValue = "0")
    private Long id = 0L;
    /**
     * 类型id
     */
    @Builder.Default
    @Schema(title = "标签ID", defaultValue = "0")
    private Long typeId = 0L;
}
