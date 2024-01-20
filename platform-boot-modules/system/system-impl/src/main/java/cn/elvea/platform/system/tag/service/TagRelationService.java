package cn.elvea.platform.system.tag.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagRelationEntity;
import cn.elvea.platform.system.tag.model.request.TagRelationDeleteRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationSaveRequest;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagRelationService extends CachingEntityService<TagRelationEntity, Long> {

    /**
     * 查询目标实体关联
     */
    List<TagRelationEntity> findRelations(TagRelationRequest request);

    /**
     * 保存目标实体关联
     */
    void saveRelation(TagRelationSaveRequest request);

    /**
     * 删除目标实体关联
     */
    void deleteRelation(TagRelationRequest request);

    /**
     * 删除关联信息
     */
    void deleteRelation(TagRelationDeleteRequest request);

}
