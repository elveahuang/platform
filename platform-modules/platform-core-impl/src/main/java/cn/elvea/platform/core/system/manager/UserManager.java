package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.UserEntity;

/**
 * UserManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface UserManager extends EntityService<UserEntity, Long> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return {@link UserEntity}
     */
    UserEntity findByUsername(String username);

    /**
     * 根据邮箱查询
     *
     * @param email 用户名
     * @return {@link UserEntity}
     */
    UserEntity findByEmail(String email);

    /**
     * 根据手机号码查询
     *
     * @param mobileCountryCode 手机国家区号
     * @param mobile            手机号码
     * @return {@link UserEntity}
     */
    UserEntity findByMobile(String mobileCountryCode, String mobile);

    /**
     * 清空缓存
     */
    void clearCache();

}
