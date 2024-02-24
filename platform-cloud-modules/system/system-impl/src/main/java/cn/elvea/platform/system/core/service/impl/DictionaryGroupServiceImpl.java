package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.mapper.DictionaryTypeMapper;
import cn.elvea.platform.system.core.model.entity.DictionaryTypeEntity;
import cn.elvea.platform.system.core.service.DictionaryTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryTypeService
 * @since 24.1.0
 */
@Service
public class DictionaryGroupServiceImpl
        extends BaseCachingEntityService<DictionaryTypeEntity, Long, DictionaryTypeMapper>
        implements DictionaryTypeService {
}
