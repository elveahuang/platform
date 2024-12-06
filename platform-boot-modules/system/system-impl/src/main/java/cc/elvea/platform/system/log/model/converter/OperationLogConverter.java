package cc.elvea.platform.system.log.model.converter;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface OperationLogConverter {

    OperationLogEntity dto2Entity(OperationLogDto dto);

}
