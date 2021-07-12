package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserRoleRelationManagerTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class UserRoleRelationManagerTests extends BaseTests {

    @Autowired
    UserRoleRelationManager manager;

    @Test
    public void test() {
        List<UserRoleRelationEntity> relationEntityList = this.manager.findByUserId(1L);
        Assertions.assertTrue(CollectionUtils.isEmpty(relationEntityList));
//
//        relationEntityList = this.manager.updateByTenantId(1L, Arrays.asList(2L, 3L, 4L));
//        Assertions.assertTrue(CollectionUtils.isNotEmpty(relationEntityList));
    }

}
