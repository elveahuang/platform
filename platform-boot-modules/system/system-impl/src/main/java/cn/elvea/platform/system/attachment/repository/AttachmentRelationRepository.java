package cn.elvea.platform.system.attachment.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.attachment.model.entity.AttachmentRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface AttachmentRelationRepository extends BaseEntityRepository<AttachmentRelationEntity, Long> {
}
