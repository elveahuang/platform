package cn.elvea.platform.core.system.service;

import cn.elvea.platform.core.system.domain.dto.UserDto;

/**
 * UserService
 *
 * @author elvea
 * @since 0.0.1
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     */
    UserDto findByUsername(String username);

}
