package cn.elvea.platform.system.attachment.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import cn.elvea.platform.system.attachment.repository.AttachmentTypeRepository;
import cn.elvea.platform.system.attachment.service.AttachmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
public class AttachmentTypeServiceImpl
        extends BaseCachingEntityService<AttachmentTypeEntity, Long, AttachmentTypeRepository> implements AttachmentTypeService {
}
