package cn.elvea.platform.system.tag.model.request;

import cn.elvea.platform.commons.core.web.request.PageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagTypeSearchRequest extends PageRequest {
}
