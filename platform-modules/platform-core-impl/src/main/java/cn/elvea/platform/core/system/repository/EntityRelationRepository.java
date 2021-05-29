package cn.elvea.platform.core.system.repository;

import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import cn.elvea.platform.core.system.domain.entity.EntityRelationEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * EntityRelationRepository
 *
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface EntityRelationRepository extends JdbcRepository<EntityRelationEntity, Long> {

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    @Query("select * from sys_entity_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    List<EntityRelationEntity> getParents(String relationType, long entityId);

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
    @Query("select * from sys_entity_relation r where r.relation_type = :relationType and r.entity_id = :entityId and r.parent_ind = 1")
    List<EntityRelationEntity> getDirectParents(String relationType, long entityId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    @Query("select * from sys_entity_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    List<EntityRelationEntity> getChildren(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
    @Query("select * from sys_entity_relation r where r.relation_type = :relationType and r.entity_id = :entityId and r.parent_ind = 1")
    List<EntityRelationEntity> getDirectChildren(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     */
    @Modifying
    @Query("delete from sys_entity_relation r where r.relation_type = :relationType and r.ancestor_id = :ancestorId")
    void deleteAsAncestor(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     */
    @Modifying
    @Query("delete from sys_entity_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    void deleteAsChild(String relationType, long entityId);

}
