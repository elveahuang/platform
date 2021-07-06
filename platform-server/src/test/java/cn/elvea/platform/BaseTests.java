package cn.elvea.platform;

import cn.elvea.platform.server.Application;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * BaseTests
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public abstract class BaseTests {
}
