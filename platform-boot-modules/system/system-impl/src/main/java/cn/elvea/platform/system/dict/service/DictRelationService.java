package cn.elvea.platform.system.dict.service;

import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.dict.model.entity.DictRelationEntity;
import cn.elvea.platform.system.dict.repository.DictRelationRepository;

/**
 * @author elvea
 */
public interface DictRelationService extends CachingEntityService<DictRelationEntity, Long>, EnhancedEntityService<DictRelationEntity, Long, DictRelationRepository> {

    /**
     * 根据标签id删除标签关系
     */
    Boolean hasTagRelation(Long tagId);

    /**
     * 根据标签id删除标签关系
     */
    void deleteByTagId(Long tagId, Long tagTypeId);

}
