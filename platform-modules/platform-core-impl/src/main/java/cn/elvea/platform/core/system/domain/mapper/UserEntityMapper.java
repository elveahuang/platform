package cn.elvea.platform.core.system.domain.mapper;

import cn.elvea.platform.core.system.domain.dto.UserDto;
import cn.elvea.platform.core.system.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * UserDomainMapper
 *
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface UserEntityMapper {

    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);

    @Mapping(target = "roleIds", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserDto entity2Dto(UserEntity entity);

}
