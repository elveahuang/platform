package cn.elvea.platform.commons.core.extensions.log.store;

import cn.elvea.platform.commons.core.extensions.log.dto.OperationLogDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class DefaultLogStore implements LogStore {

    /**
     * @see LogStore#saveLog(OperationLogDto)
     */
    @Override
    public void saveLog(OperationLogDto dto) {
        try {
            log.info(dto.toString());
        } catch (Exception e) {
            log.error("save log error.", e);
        }
    }

}
