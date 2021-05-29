package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.domain.entity.TenantEntity;
import cn.elvea.platform.core.system.manager.TenantManager;
import cn.elvea.platform.core.system.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TenantManager
 *
 * @author elvea
 * @see TenantManager
 * @since 0.0.1
 */
@Slf4j
@Service
public class TenantManagerImpl extends AbstractEntityService<TenantEntity, Long, TenantRepository> implements TenantManager {
}
