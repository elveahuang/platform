package cn.elvea.platform.system.mall.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.mall.model.entity.PayTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface PayTypeRepository extends BaseEntityRepository<PayTypeEntity, Long> {
}
