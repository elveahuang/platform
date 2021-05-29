package cn.elvea.platform.core.system.service;

import cn.elvea.platform.core.system.manager.TenantManager;
import cn.elvea.platform.core.system.service.TenantService;
import org.springframework.stereotype.Service;

/**
 * TenantService
 *
 * @author elvea
 * @see TenantService
 * @since 0.0.1
 */
@Service
public class TenantServiceImpl implements TenantService {

    private final TenantManager tenantManager;

    public TenantServiceImpl(TenantManager tenantManager) {
        this.tenantManager = tenantManager;
    }

}
