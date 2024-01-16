package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.dto.UserCheckEmailDto;
import cn.elvea.platform.system.core.model.dto.UserCheckMobileDto;
import cn.elvea.platform.system.core.model.dto.UserCheckUsernameDto;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.model.form.UserForm;

/**
 * @author elvea
 * @see EntityService
 * @since 24.1.0
 */
public interface UserService extends CachingEntityService<UserEntity, Long> {

    /**
     * 根据username检查用户是否存在
     */
    boolean checkUsername(UserCheckUsernameDto dto);

    /**
     * 根据邮箱查看用户是否存在
     */
    boolean checkEmail(UserCheckEmailDto dto);

    /**
     * 根据用户手机查找用户是否存在
     */
    boolean checkMobile(UserCheckMobileDto dto);

    /**
     * 根据用户名查找用户
     */
    UserEntity findByUsername(String username);

    /**
     * 根据邮箱查找用户
     */
    UserEntity findByEmail(String email);

    /**
     * 根据手机查找用户
     */
    UserEntity findByMobile(String mobileCountryCode, String mobileNumber);

    /**
     * 获取系统管理员
     */
    UserEntity getSystemAdministrator();

    /**
     * 保存用户
     */
    void saveUser(UserForm form);

}
