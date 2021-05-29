package cn.elvea.platform.commons.redis.cache;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * UserServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void baseTests() {
        User user = userService.get(1L);
        User newUser = userService.get(1L);
    }

}
