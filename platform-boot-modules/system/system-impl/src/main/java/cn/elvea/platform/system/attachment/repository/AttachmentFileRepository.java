package cn.elvea.platform.system.attachment.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.attachment.model.entity.AttachmentFileEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface AttachmentFileRepository extends BaseEntityRepository<AttachmentFileEntity, Long> {
}
