package cc.elvea.platform.system.security.model.converter;

import cc.elvea.platform.system.security.model.dto.ClientDto;
import cc.elvea.platform.system.security.model.entity.ClientEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper(componentModel = SPRING)
public interface ClientConverter {

    ClientDto entity2Dto(ClientEntity entity);

}
