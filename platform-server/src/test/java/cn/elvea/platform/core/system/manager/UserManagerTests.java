package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.core.system.domain.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserManagerTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class UserManagerTests extends BaseTests {

    @Autowired
    UserManager userManager;

    @Test
    public void test() {
        UserEntity userEntity1 = this.userManager.findById(1L);
        UserEntity userEntity2 = this.userManager.findByUsername("admin");
        UserEntity userEntity3 = this.userManager.findByEmail("admin@elvea.cn");
        UserEntity userEntity4 = this.userManager.findByMobile("86", "13500000000");
        Assertions.assertNotNull(userEntity1);
        Assertions.assertNotNull(userEntity2);
        Assertions.assertNotNull(userEntity3);
        Assertions.assertNotNull(userEntity4);

        UserEntity userEntity5 = this.userManager.findById(2L);
        UserEntity userEntity6 = this.userManager.findByUsername("test");
        UserEntity userEntity7 = this.userManager.findByEmail("test@elvea.cn");
        UserEntity userEntity8 = this.userManager.findByMobile("86", "13500000001");
        Assertions.assertNotNull(userEntity5);
        Assertions.assertNotNull(userEntity6);
        Assertions.assertNotNull(userEntity7);
        Assertions.assertNotNull(userEntity8);
    }

}
