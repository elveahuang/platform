package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.mapper.TagTypeMapper;
import cn.elvea.platform.system.core.model.entity.TagTypeEntity;
import cn.elvea.platform.system.core.service.TagTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagTypeService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Service
public class TagTypeServiceImpl extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeMapper> implements TagTypeService {
}
