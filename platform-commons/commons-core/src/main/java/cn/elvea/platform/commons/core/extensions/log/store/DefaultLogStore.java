package cn.elvea.platform.commons.core.extensions.log.store;

import cn.elvea.platform.commons.core.extensions.log.dto.OperationLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class DefaultLogStore implements LogStore {

    /**
     * @see LogStore#saveOperationLog(OperationLogDto)
     */
    @Override
    @Async
    public void saveOperationLog(OperationLogDto dto) {
        try {
            log.info(dto.toString());
        } catch (Exception e) {
            log.error("save log error.", e);
        }
    }

}
