package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface EntityRelationRepository extends BaseEntityRepository<EntityRelationEntity, Long> {
}
