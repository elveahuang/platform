package cn.elvea.platform.system.i18n.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.i18n.model.entity.LabelEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface LabelRepository extends BaseEntityRepository<LabelEntity, Long> {
}
