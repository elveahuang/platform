package cn.elvea.platform.system.tag.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.tag.model.entity.TagRelationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface TagRelationRepository extends BaseEntityRepository<TagRelationEntity, Long> {
}
