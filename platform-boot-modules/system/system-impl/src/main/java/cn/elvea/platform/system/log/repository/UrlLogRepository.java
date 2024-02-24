package cn.elvea.platform.system.log.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.log.model.entity.UrlLogEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface UrlLogRepository extends BaseEntityRepository<UrlLogEntity, Long> {
}
