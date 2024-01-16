package cn.elvea.platform.system.log.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.log.model.entity.OperationLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface OperationLogRepository extends BaseEntityRepository<OperationLogEntity, Long> {
}
