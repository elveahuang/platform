package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.EntityRelationEntity;

import java.util.List;

/**
 * EntityRelationManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface EntityRelationManager extends EntityService<EntityRelationEntity, Long> {

    /**
     * 获取所有上级
     *
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    List<EntityRelationEntity> getParents(String relationType, long entityId);

    /**
     * 获取所有直属上级
     *
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    List<EntityRelationEntity> getDirectParents(String relationType, long entityId);

    /**
     * 获取所有下级
     *
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    List<EntityRelationEntity> getChildren(String relationType, long ancestorId);

    /**
     * 获取所有直属下级
     *
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    List<EntityRelationEntity> getDirectChildren(String relationType, long ancestorId);

    /**
     * 更新实体关联
     *
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @param ancestorId   祖先ID
     */
    void updateEntityRelations(String relationType, Long entityId, Long ancestorId);

    /**
     * 删除下级关联
     */
    void deleteAsAncestor(String relationType, Long ancestorId);

    /**
     * 删除上级关联
     */
    void deleteAsChild(String relationType, Long entityId);

    /**
     * 清空缓存
     */
    void clearCache();

}
