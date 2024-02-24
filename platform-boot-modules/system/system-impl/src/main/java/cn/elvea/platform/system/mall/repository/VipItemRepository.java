package cn.elvea.platform.system.mall.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.mall.model.entity.VipItemEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface VipItemRepository extends BaseEntityRepository<VipItemEntity, Long> {
}
