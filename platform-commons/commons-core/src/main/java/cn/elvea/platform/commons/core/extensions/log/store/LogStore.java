package cn.elvea.platform.commons.core.extensions.log.store;

import cn.elvea.platform.commons.core.extensions.log.dto.OperationLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LogStore {

    /**
     * 保存操作日志
     *
     * @param dto {@link OperationLogDto}
     */
    void saveLog(OperationLogDto dto) throws Exception;

}
