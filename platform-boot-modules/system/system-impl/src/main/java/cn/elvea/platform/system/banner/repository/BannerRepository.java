package cn.elvea.platform.system.banner.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.banner.model.entity.BannerEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface BannerRepository extends BaseEntityRepository<BannerEntity, Long> {
}
