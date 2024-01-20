package cn.elvea.platform.system.tag.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface TagRepository extends BaseEntityRepository<TagEntity, Long> {
}
