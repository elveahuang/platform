package cn.elvea.platform.core.system.mapper;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.persistence.mybatis.page.PageList;
import cn.elvea.platform.core.system.domain.entity.EntityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * EntityRelationMapperTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class EntityRelationMapperTests extends BaseTests {

    @Autowired
    EntityRelationMapper entityRelationMapper;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationMapper);

        Pageable pageable = PageRequest.of(1, 1);

        List<EntityRelationEntity> list = this.entityRelationMapper.searchByPageable(pageable);
        PageList<EntityRelationEntity> pageList = this.entityRelationMapper.searchByPage(pageable);

        Assertions.assertTrue(CollectionUtils.isNotEmpty(pageList));
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
        Page<EntityRelationEntity> page = pageList.getPage();
        Assertions.assertNotNull(page);
    }

}
