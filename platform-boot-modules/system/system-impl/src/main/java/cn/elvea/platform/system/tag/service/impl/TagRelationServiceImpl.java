package cn.elvea.platform.system.tag.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagRelationEntity;
import cn.elvea.platform.system.tag.repository.TagRelationRepository;
import cn.elvea.platform.system.tag.service.TagRelationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class TagRelationServiceImpl extends BaseCachingEntityService<TagRelationEntity, Long, TagRelationRepository> implements TagRelationService {

    @Override
    public Boolean hasTagRelation(Long tagId) {
        return false;
    }

    @Override
    public void deleteByTagId(Long tagId, Long tagTypeId) {

    }

}
