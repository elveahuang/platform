package cn.elvea.platform.core.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * UserMapper
 *
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface UserMapper {

    Date search();

}
