package cn.elvea.platform.system.dict.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.dict.model.entity.DictTypeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface DictTypeRepository extends BaseEntityRepository<DictTypeEntity, Long> {
}
