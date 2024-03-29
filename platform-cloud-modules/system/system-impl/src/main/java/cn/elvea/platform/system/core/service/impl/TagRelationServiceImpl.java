package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.mapper.TagRelationMapper;
import cn.elvea.platform.system.core.model.entity.TagRelationEntity;
import cn.elvea.platform.system.core.service.TagRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagRelationService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Service
@Slf4j
public class TagRelationServiceImpl extends BaseCachingEntityService<TagRelationEntity, Long, TagRelationMapper> implements TagRelationService {
}
