package cn.elvea.platform.system.dict.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.system.dict.model.entity.DictRelationEntity;
import cn.elvea.platform.system.dict.repository.DictRelationRepository;
import cn.elvea.platform.system.dict.service.DictRelationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictRelationServiceImpl
        extends BaseCachingEntityService<DictRelationEntity, Long, DictRelationRepository>
        implements EnhancedEntityService<DictRelationEntity, Long, DictRelationRepository>, DictRelationService {
}
