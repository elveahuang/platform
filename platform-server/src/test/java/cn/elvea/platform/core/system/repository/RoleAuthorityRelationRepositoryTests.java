package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.BaseWebTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserRoleRelationRepositoryTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class RoleAuthorityRelationRepositoryTests extends BaseWebTests {

    @Autowired
    private RoleAuthorityRelationRepository repository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.repository);

        this.repository.findByRoleId(1L);
    }

}
