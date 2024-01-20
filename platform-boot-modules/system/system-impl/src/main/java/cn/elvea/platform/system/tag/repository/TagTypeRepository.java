package cn.elvea.platform.system.tag.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.tag.model.entity.TagTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface TagTypeRepository extends BaseEntityRepository<TagTypeEntity, Long> {
}
