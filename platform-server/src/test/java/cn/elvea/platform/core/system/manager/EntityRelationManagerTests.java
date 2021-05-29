package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.elvea.platform.core.system.SystemConstants.*;

/**
 * EntityRelationManagerTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class EntityRelationManagerTests extends BaseTests {

    @Autowired
    EntityRelationManager entityRelationManager;

    @Test
    public void test() {
        Assertions.assertNotNull(this.entityRelationManager);

        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_DPT_PARENT_DPT, 2L, 1L);
        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_DPT_PARENT_DPT, 3L, 2L);
        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_DPT_PARENT_DPT, 4L, 3L);

        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_PST_PARENT_PST, 2L, 1L);
        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_PST_PARENT_PST, 3L, 2L);
        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_PST_PARENT_PST, 4L, 3L);

        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_USR_CURRENT_DPT, 1L, 4L);
        this.entityRelationManager.updateEntityRelations(ENTITY_RELATION_USR_CURRENT_PST, 1L, 4L);
    }

}
