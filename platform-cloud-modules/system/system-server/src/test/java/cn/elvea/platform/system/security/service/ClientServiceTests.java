package cn.elvea.platform.system.security.service;

import cn.elvea.platform.system.BaseTests;
import cn.elvea.platform.system.security.model.entity.ClientEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceTests extends BaseTests {

    @Autowired
    ClientService clientService;

    @Test
    public void loadCache() {
        this.clientService.refreshCache();
    }

    @Test
    public void clearCache() {
        this.clientService.clearCache();
    }

    @Test
    public void base() {
        Assertions.assertNotNull(clientService);
        ClientEntity client = clientService.findCacheById(1L);
        Assertions.assertNotNull(client);

        client = clientService.findById(1L);
        Assertions.assertNotNull(client);

        client = clientService.findClientByClientId("webapp");
        Assertions.assertNotNull(client);
    }

}
