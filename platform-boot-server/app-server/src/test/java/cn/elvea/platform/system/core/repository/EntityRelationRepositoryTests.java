package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public class EntityRelationRepositoryTests extends BaseTests {

    @Autowired
    EntityRelationRepository entityRelationRepository;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationRepository);

        List<EntityRelationEntity> list = this.entityRelationRepository.findAll();
        Assertions.assertTrue(CollectionUtils.isNotEmpty(list));
    }

}
