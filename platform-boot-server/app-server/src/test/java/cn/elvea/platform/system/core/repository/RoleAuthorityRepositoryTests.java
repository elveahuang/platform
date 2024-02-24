package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public class RoleAuthorityRepositoryTests extends BaseTests {

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.roleAuthorityRepository);

        List<RoleAuthorityEntity> list = this.roleAuthorityRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
