package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.BaseWebTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * EntityRelationRepositoryTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class EntityRelationRepositoryTests extends BaseWebTests {

    private final EntityRelationRepository entityRelationRepository;

    @Autowired
    public EntityRelationRepositoryTests(EntityRelationRepository entityRelationRepository) {
        this.entityRelationRepository = entityRelationRepository;
    }

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationRepository);
    }

}
