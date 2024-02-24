package cn.elvea.platform.system.log.api.impl;

import cn.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.logging.domain.OperationLogDto;
import cn.elvea.platform.commons.logging.domain.UrlLogDto;
import cn.elvea.platform.system.log.api.LogApi;
import cn.elvea.platform.system.log.service.CaptchaLogAmqpService;
import cn.elvea.platform.system.log.service.OperationLogAmqpService;
import cn.elvea.platform.system.log.service.UrlLogAmqpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class LogApiImpl implements LogApi {

    private final CaptchaLogAmqpService captchaLogAmqpService;

    private final OperationLogAmqpService operationLogAmqpService;

    private final UrlLogAmqpService urlLogAmqpService;

    @Override
    public void saveLogLog(UrlLogDto captchaLog) throws Exception {
        this.urlLogAmqpService.send(captchaLog);
    }

    @Override
    public void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception {
        this.captchaLogAmqpService.send(captchaLog);
    }

    @Override
    public void saveOperationLog(OperationLogDto operationLog) throws Exception {
        this.operationLogAmqpService.send(operationLog);
    }

}
