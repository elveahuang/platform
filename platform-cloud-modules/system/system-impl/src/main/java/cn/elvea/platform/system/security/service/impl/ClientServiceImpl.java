package cn.elvea.platform.system.security.service.impl;

import cn.elvea.platform.commons.cache.CacheKey;
import cn.elvea.platform.commons.data.domain.IdEntity;
import cn.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.utils.ObjectUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.system.security.cache.ClientCacheKeyGenerator;
import cn.elvea.platform.system.security.model.entity.ClientEntity;
import cn.elvea.platform.system.security.repository.ClientMapper;
import cn.elvea.platform.system.security.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see ClientService
 * @since 24.1.0
 */
@Slf4j
@Service
public class ClientServiceImpl extends BaseCachingEntityService<ClientEntity, Long, ClientMapper> implements ClientService {

    private final ClientCacheKeyGenerator cacheKeyGenerator = new ClientCacheKeyGenerator();

    @Override
    public ClientCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see ClientService#findClientById(Long)
     */
    @Override
    public ClientEntity findClientById(Long id) {
        return this.findCacheById(id);
    }

    /**
     * @see ClientService#findClientByClientId(String)
     */
    @Override
    public ClientEntity findClientByClientId(String clientId) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByClientId(clientId);
        return getCacheService().get(cacheKey, k -> this.lambdaQuery().eq(ClientEntity::getClientId, clientId).one());
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(ClientEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getClientId())) {
                getCacheService().set(getCacheKeyGenerator().keyByClientId(model.getClientId()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(ClientEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getClientId())) {
                getCacheService().delete(getCacheKeyGenerator().keyByClientId(model.getClientId()));
            }
        }
    }

}
