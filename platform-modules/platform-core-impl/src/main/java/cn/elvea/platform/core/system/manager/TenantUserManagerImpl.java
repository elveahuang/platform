package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.domain.entity.TenantUserEntity;
import cn.elvea.platform.core.system.manager.TenantUserManager;
import cn.elvea.platform.core.system.repository.TenantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TenantUserManager
 *
 * @author elvea
 * @see TenantUserManager
 * @since 0.0.1
 */
@Service
@Slf4j
public class TenantUserManagerImpl extends AbstractEntityService<TenantUserEntity, Long, TenantUserRepository> implements TenantUserManager {
}
