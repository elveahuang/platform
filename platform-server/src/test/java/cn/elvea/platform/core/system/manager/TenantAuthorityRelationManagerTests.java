package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.core.system.domain.entity.TenantAuthorityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * TenantAuthorityRelationManagerTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class TenantAuthorityRelationManagerTests extends BaseTests {

    @Autowired
    TenantAuthorityRelationManager manager;

    @Test
    public void test() {
        List<TenantAuthorityRelationEntity> relationEntityList = this.manager.findByTenantId(1L);
        Assertions.assertTrue(CollectionUtils.isEmpty(relationEntityList));

        relationEntityList = this.manager.updateByTenantId(1L, Arrays.asList(2L, 3L, 4L));
        Assertions.assertTrue(CollectionUtils.isNotEmpty(relationEntityList));
    }

}
