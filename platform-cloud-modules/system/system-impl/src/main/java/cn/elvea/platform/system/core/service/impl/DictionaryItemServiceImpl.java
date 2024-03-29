package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.mapper.DictionaryItemMapper;
import cn.elvea.platform.system.core.model.entity.DictionaryItemEntity;
import cn.elvea.platform.system.core.service.DictionaryItemService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryItemService
 * @since 24.1.0
 */
@Service
public class DictionaryItemServiceImpl extends BaseCachingEntityService<DictionaryItemEntity, Long, DictionaryItemMapper> implements DictionaryItemService {
}
