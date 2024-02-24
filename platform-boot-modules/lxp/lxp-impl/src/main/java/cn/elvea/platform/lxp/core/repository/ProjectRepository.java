package cn.elvea.platform.lxp.core.repository;

import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.lxp.core.model.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface ProjectRepository extends BaseEntityRepository<ProjectEntity, Long> {
}
