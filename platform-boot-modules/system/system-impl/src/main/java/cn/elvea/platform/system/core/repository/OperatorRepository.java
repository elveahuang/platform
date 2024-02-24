package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.core.model.entity.OperatorEntity;
import org.springframework.stereotype.Repository;

/**
 * @since 24.1.0
 */
@Repository
public interface OperatorRepository extends BaseEntityRepository<OperatorEntity, Long> {
}
