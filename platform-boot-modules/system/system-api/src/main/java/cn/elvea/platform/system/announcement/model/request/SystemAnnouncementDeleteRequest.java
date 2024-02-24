package cn.elvea.platform.system.announcement.model.request;

import cn.elvea.platform.commons.web.request.Request;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class SystemAnnouncementDeleteRequest extends Request {
    private Long[] ids;
}
