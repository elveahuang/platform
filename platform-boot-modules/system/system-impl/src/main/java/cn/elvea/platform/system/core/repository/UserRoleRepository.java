package cn.elvea.platform.system.core.repository;

import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.system.core.model.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Repository
public interface UserRoleRepository extends BaseEntityRepository<UserRoleEntity, Long> {
}
