package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.mapper.DictionaryRelationMapper;
import cn.elvea.platform.system.core.model.entity.DictionaryRelationEntity;
import cn.elvea.platform.system.core.service.DictionaryRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryRelationService
 * @since 24.1.0
 */
@Service
@Slf4j
public class DictionaryRelationServiceImpl extends BaseCachingEntityService<DictionaryRelationEntity, Long, DictionaryRelationMapper>
        implements DictionaryRelationService {
}
