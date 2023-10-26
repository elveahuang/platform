package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface UserApi {

    /**
     * 获取用户详情
     */
    UserInfoDto getUserInfo(String username);

    /**
     * 获取用户登录详情
     */
    UserLoginDto findByUsername(String username);

    /**
     * 获取用户登录详情
     */
    UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber);

    /**
     * 获取用户登录详情
     */
    UserLoginDto findByEmail(String email);

}
