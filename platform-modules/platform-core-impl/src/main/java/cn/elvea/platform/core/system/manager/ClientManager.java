package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.core.system.domain.entity.ClientEntity;

/**
 * ClientManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface ClientManager extends EntityService<ClientEntity, Long> {

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return {@link ClientEntity}
     */
    ClientEntity findById(Long id);

    /**
     * 根据客户端标识查询
     *
     * @param clientId 客户端标识
     * @return {@link ClientEntity}
     */
    ClientEntity findByClientId(String clientId);

    /**
     * 清空缓存
     */
    void clearCache();

}
