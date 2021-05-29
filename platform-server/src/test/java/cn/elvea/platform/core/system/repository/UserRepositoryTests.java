package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.BaseWebTests;
import cn.elvea.platform.core.system.domain.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserRepositoryTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class UserRepositoryTests extends BaseWebTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userRepository);
    }

    @Test
    public void testFindByUsername() {
        UserEntity userEntity = this.userRepository.findByUsername("admin");
        Assertions.assertNotNull(userEntity);
    }

    @Test
    public void testFindByEmail() {
        UserEntity userEntity = this.userRepository.findByEmail("platform@host.com");
        Assertions.assertNotNull(userEntity);
    }

    @Test
    public void testFindByMobile() {
        UserEntity userEntity = this.userRepository.findByMobileCountryCodeAndMobile("86", "13800138000");
        Assertions.assertNotNull(userEntity);
    }

}
