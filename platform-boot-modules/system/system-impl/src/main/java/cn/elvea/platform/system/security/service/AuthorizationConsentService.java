package cn.elvea.platform.system.security.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.security.model.entity.AuthorizationConsentEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @since 24.1.0
 */
public interface AuthorizationConsentService extends CachingEntityService<AuthorizationConsentEntity, Long> {

    AuthorizationConsentEntity findByKey(String clientId, String principalName);

    void deleteByKey(String clientId, String principalName);

}
