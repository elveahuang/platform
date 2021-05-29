package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.ClientEntity;
import cn.elvea.platform.core.system.manager.ClientManager;
import cn.elvea.platform.core.system.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ClientManager
 *
 * @author elvea
 * @see ClientManager
 * @since 0.0.1
 */
@Slf4j
@Service
@CacheConfig(cacheNames = SystemConstants.CACHE_CLIENT)
public class ClientManagerImpl extends AbstractEntityService<ClientEntity, Long, ClientRepository> implements ClientManager {

    /**
     * @see ClientManager#findById(Long)
     */
    @Override
    @Cacheable(key = "'id-' + #id")
    public ClientEntity findById(Long id) {
        return super.findById(id);
    }

    /**
     * @see ClientManager#findByClientId(String)
     */
    @Override
    @Cacheable(key = "'client-id-' + #clientId")
    public ClientEntity findByClientId(String clientId) {
        return this.repository.findByClientId(clientId);
    }

    /**
     * @see ClientManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear client cache [{}]...", SystemConstants.CACHE_CLIENT);
        }
    }

}
