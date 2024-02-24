package cn.elvea.platform.system.log.api;

import cn.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.logging.domain.OperationLogDto;
import cn.elvea.platform.commons.logging.domain.UrlLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LogApi {

    void saveLogLog(UrlLogDto captchaLog) throws Exception;

    void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception;

    void saveOperationLog(OperationLogDto operationLog) throws Exception;

}
