package cn.elvea.platform.system.attachment.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.attachment.model.entity.AttachmentTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 0.0.1
 */
@Repository
public interface AttachmentTypeRepository extends BaseEntityRepository<AttachmentTypeEntity, Long> {
}
