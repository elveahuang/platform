package cn.elvea.platform.system.tag.service;

import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagRelationEntity;
import cn.elvea.platform.system.tag.repository.TagRelationRepository;

/**
 * @author elvea
 */
public interface TagRelationService extends CachingEntityService<TagRelationEntity, Long>, EnhancedEntityService<TagRelationEntity, Long, TagRelationRepository> {

    /**
     * 根据标签id删除标签关系
     */
    Boolean hasTagRelation(Long tagId);

    /**
     * 根据标签id删除标签关系
     */
    void deleteByTagId(Long tagId, Long tagTypeId);

}
