package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface UserRepository extends JdbcRepository<UserEntity, Long> {

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
    UserEntity findByMobileCountryCodeAndMobile(String mobileCountryCode, String mobile);

}
