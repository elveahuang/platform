package cn.elvea.platform.system.message.request;

import cn.elvea.platform.commons.core.web.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeSearchRequest extends PageRequest {

    @Schema(title = "用户ID", defaultValue = "0")
    @Builder.Default
    private Long userId = 0L;

}
