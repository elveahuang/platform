package cn.elvea.platform.core.system.domain.mapper;

import cn.elvea.platform.core.system.domain.dto.ClientDto;
import cn.elvea.platform.core.system.domain.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClientDomainMapper
 *
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface ClientEntityMapper {

    ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);

    ClientDto entity2Dto(ClientEntity entity);

}
