package cn.elvea.platform.security;

import cn.elvea.platform.system.security.api.ClientApi;
import cn.elvea.platform.system.security.model.dto.ClientDto;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.security.utils.OAuth2Utils.toRegisteredClient;
import static cn.elvea.platform.security.utils.OAuth2Utils.toRegisteredClientDto;

/**
 * @author elvea
 * @since 24.1.0
 */
@Service
@AllArgsConstructor
public class CustomOAuth2ClientService implements RegisteredClientRepository {

    private final ClientApi clientApi;

    private final TokenSettings tokenSettings;

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientApi.save(toRegisteredClientDto(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        ClientDto clientDto = this.clientApi.findById(Long.valueOf(id));
        return toRegisteredClient(clientDto, tokenSettings);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientDto clientDto = this.clientApi.findByClientId(clientId);
        return toRegisteredClient(clientDto, tokenSettings);
    }

}
