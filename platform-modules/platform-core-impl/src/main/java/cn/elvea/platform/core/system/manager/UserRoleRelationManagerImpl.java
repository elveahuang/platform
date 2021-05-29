package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.domain.entity.UserRoleRelationEntity;
import cn.elvea.platform.core.system.manager.UserRoleRelationManager;
import cn.elvea.platform.core.system.repository.UserRoleRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * UserRoleRelationManager
 *
 * @author elvea
 * @see UserRoleRelationManager
 * @since 0.0.1
 */
@Service
@Slf4j
public class UserRoleRelationManagerImpl extends AbstractEntityService<UserRoleRelationEntity, Long, UserRoleRelationRepository> implements UserRoleRelationManager {
}
