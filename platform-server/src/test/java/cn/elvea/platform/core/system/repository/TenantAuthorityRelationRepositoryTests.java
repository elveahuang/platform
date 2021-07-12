package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.BaseWebTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TenantAuthorityRelationRepositoryTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class TenantAuthorityRelationRepositoryTests extends BaseWebTests {

    @Autowired
    private TenantAuthorityRelationRepository repository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.repository);
    }

}
