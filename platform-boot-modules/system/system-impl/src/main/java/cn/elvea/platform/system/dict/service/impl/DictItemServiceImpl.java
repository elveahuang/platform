package cn.elvea.platform.system.dict.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.system.dict.model.entity.DictItemEntity;
import cn.elvea.platform.system.dict.repository.DictItemRepository;
import cn.elvea.platform.system.dict.service.DictItemService;
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
public class DictItemServiceImpl
        extends BaseCachingEntityService<DictItemEntity, Long, DictItemRepository>
        implements EnhancedEntityService<DictItemEntity, Long, DictItemRepository>, DictItemService {
}
