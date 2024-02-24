package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public class UserRepositoryTests extends BaseTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.userRepository);

        List<UserEntity> list = this.userRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
