package cn.elvea.platform.system.tag.model.request;

import cn.elvea.platform.commons.core.web.request.Request;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class TagTypeDeleteRequest extends Request {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long[] ids;
}
