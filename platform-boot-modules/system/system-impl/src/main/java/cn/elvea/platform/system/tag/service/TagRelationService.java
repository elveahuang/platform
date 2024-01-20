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

    List<TagRelationEntity> findRelations(TagRelationRequest request);

    /**
     * 根据业务实体类型ID和业务实体ID删除所有关联信息
     */
    void deleteRelation(TagRelationRequest request);

    /**
     * 根据标签类型ID和标签ID删除所有关联信息
     */
    void deleteRelation(TagRelationDeleteRequest request);

    /**
     * 保存业务实体和标签的关联关系
     */
    void saveRelation(TagRelationSaveRequest request);

}
