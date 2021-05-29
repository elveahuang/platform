package cn.elvea.platform.core.system.service;

import cn.elvea.platform.core.system.domain.dto.ClientDto;
import cn.elvea.platform.core.system.domain.entity.ClientEntity;
import cn.elvea.platform.core.system.domain.mapper.ClientEntityMapper;
import cn.elvea.platform.core.system.manager.ClientManager;
import cn.elvea.platform.core.system.service.ClientService;
import org.springframework.stereotype.Service;

/**
 * ClientService
 *
 * @author elvea
 * @see ClientService
 * @since 0.0.1
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientManager clientManager;

    public ClientServiceImpl(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    /**
     * @see ClientService#findById(Long)
     */
    @Override
    public ClientDto findById(Long id) {
        ClientEntity entity = clientManager.findById(id);
        return ClientEntityMapper.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see ClientService#findByClientId(String)
     */
    @Override
    public ClientDto findByClientId(String clientId) {
        ClientEntity entity = clientManager.findByClientId(clientId);
        return ClientEntityMapper.INSTANCE.entity2Dto(entity);
    }

}
