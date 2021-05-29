package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.domain.entity.RoleAuthorityRelationEntity;
import cn.elvea.platform.core.system.manager.RoleAuthorityRelationManager;
import cn.elvea.platform.core.system.repository.RoleAuthorityRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * RoleAuthorityRelationManager
 *
 * @author elvea
 * @see RoleAuthorityRelationManager
 * @since 0.0.1
 */
@Slf4j
@Service
public class RoleAuthorityRelationManagerImpl extends AbstractEntityService<RoleAuthorityRelationEntity, Long, RoleAuthorityRelationRepository> implements RoleAuthorityRelationManager {
}
