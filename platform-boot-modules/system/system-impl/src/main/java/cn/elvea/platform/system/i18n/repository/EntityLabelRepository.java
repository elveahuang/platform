package cn.elvea.platform.system.i18n.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.i18n.model.entity.EntityLabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface EntityLabelRepository extends BaseEntityRepository<EntityLabelEntity, Long> {
}
