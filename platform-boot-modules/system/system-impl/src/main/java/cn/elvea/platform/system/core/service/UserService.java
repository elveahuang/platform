package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.model.form.UserForm;
import cn.elvea.platform.system.core.model.request.UserCheckRequest;

/**
 * @author elvea
 * @see EntityService
 * @since 24.1.0
 */
public interface UserService extends CachingEntityService<UserEntity, Long> {

    /**
     * 检查用户名是否可用
     */
    boolean check(UserCheckRequest request);

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
