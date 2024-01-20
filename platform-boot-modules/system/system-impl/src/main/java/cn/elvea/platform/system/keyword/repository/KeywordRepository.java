package cn.elvea.platform.system.keyword.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.keyword.model.entity.KeywordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface KeywordRepository extends BaseEntityRepository<KeywordEntity, Long> {
}
