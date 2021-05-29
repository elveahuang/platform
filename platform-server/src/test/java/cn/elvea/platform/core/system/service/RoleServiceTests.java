package cn.elvea.platform.core.system.service;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ApplicationBaseTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class RoleServiceTests extends BaseTests {

    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        Assertions.assertNotNull(roleService);
    }

}
