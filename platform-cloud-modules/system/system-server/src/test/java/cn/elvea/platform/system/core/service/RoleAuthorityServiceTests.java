package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.system.BaseTests;
import cn.elvea.platform.system.core.model.dto.RoleAuthoritySaveDto;
import cn.elvea.platform.system.core.model.entity.RoleAuthorityEntity;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public class RoleAuthorityServiceTests extends BaseTests {

    @Autowired
    RoleAuthorityService roleAuthorityService;

    @Test
    public void test() {
        RoleAuthoritySaveDto dto = RoleAuthoritySaveDto.builder()
                .roleId(1L)
                .authorityIdList(Lists.newArrayList(1L))
                .build();
        roleAuthorityService.saveRoleAuthority(dto);

        List<RoleAuthorityEntity> roleAuthorityEntityList = this.roleAuthorityService.findByRoleId(BaseTests.DEFAULT_USER_ID);
        Assertions.assertTrue(CollectionUtils.isNotEmpty(roleAuthorityEntityList));
        Assertions.assertEquals(roleAuthorityEntityList.size(), 1);
    }

}
