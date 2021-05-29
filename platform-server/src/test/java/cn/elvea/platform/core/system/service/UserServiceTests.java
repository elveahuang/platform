package cn.elvea.platform.core.system.service;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.core.system.domain.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserServiceTest
 *
 * @author elvea
 * @since 0.0.1
 */
public class UserServiceTests extends BaseTests {

    @Autowired
    private UserService userService;

    @Test
    public void testFindByUsername() {
        UserDto userDto = this.userService.findByUsername("admin");
        Assertions.assertNotNull(userDto);
    }

}
