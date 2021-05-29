package cn.elvea.platform.commons.logging.provider;

import cn.elvea.platform.commons.logging.dto.OptLogDto;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * AbstractLoggingProvider
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class DefaultLoggingProvider extends AbstractLoggingProvider {

    /**
     * @see LoggingProvider#saveLog(OptLogDto)
     */
    @Override
    public void saveLog(OptLogDto dto) {
        try {
            log.debug("DefaultLoggingProvider, output log...");
            log.debug("DefaultLoggingProvider, class name : {}", dto.getClassName());
            log.debug("DefaultLoggingProvider, method : {}", dto.getMethodName());
            log.debug("DefaultLoggingProvider, annotation params : {}", dto.getAnnotationParams());
            log.debug("DefaultLoggingProvider, now date time : {}", LocalDateTime.now());
            log.debug("DefaultLoggingProvider, request ip : {}", dto.getRequestIp());
            log.debug("DefaultLoggingProvider, request uri : {}", dto.getRequestUri());
            log.debug("DefaultLoggingProvider, request params : {}", dto.getRequestParams());
            log.debug("DefaultLoggingProvider, exec time : {}", dto.getExecTime());
            log.debug("DefaultLoggingProvider, exception : {}", dto.getException());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
