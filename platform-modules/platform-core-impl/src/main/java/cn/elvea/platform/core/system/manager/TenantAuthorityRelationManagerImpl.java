package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.domain.entity.TenantAuthorityRelationEntity;
import cn.elvea.platform.core.system.manager.TenantAuthorityRelationManager;
import cn.elvea.platform.core.system.repository.TenantAuthorityRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TenantAuthorityManager
 *
 * @author elvea
 * @see TenantAuthorityRelationManager
 * @since 0.0.1
 */
@Service
@Slf4j
public class TenantAuthorityRelationManagerImpl extends AbstractEntityService<TenantAuthorityRelationEntity, Long, TenantAuthorityRelationRepository> implements TenantAuthorityRelationManager {
}
