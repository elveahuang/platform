package cn.elvea.platform.commons.logging.provider;

import cn.elvea.platform.commons.logging.dto.OptLogDto;

/**
 * LoggingProvider
 *
 * @author elvea
 * @since 0.0.1
 */
public interface LoggingProvider {

    /**
     * 保存操作日志
     *
     * @param dto {@link OptLogDto}
     */
    void saveLog(OptLogDto dto);

}
