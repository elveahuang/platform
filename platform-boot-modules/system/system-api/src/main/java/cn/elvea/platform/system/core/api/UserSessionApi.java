package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.web.R;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserSessionApi {

    R<Boolean> saveUserSession(UserSessionDto userSession) throws Exception;

}
