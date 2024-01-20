package cn.elvea.platform.system.attachment.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.attachment.model.entity.AttachmentEntity;
import cn.elvea.platform.system.attachment.repository.AttachmentRepository;
import cn.elvea.platform.system.attachment.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
public class AttachmentServiceImpl
        extends BaseCachingEntityService<AttachmentEntity, Long, AttachmentRepository>
        implements AttachmentService {
}
