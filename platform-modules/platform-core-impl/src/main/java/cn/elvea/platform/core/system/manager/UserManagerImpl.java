package cn.elvea.platform.core.system.manager;

import cn.elvea.platform.persistence.domain.BaseEntity;
import cn.elvea.platform.persistence.service.AbstractEntityService;
import cn.elvea.platform.core.system.SystemConstants;
import cn.elvea.platform.core.system.domain.entity.UserEntity;
import cn.elvea.platform.core.system.manager.UserManager;
import cn.elvea.platform.core.system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * UserManager
 *
 * @author elvea
 * @see UserManager
 * @since 0.0.1
 */
@Service
@Slf4j
@CacheConfig(cacheNames = SystemConstants.CACHE_USER)
public class UserManagerImpl extends AbstractEntityService<UserEntity, Long, UserRepository> implements UserManager {

    /**
     * @see UserManager#findById(Serializable)
     */
    @Override
    @Cacheable(key = "'id-' + #id")
    public UserEntity findById(Long id) {
        return super.findById(id);
    }

    /**
     * @see UserManager#findByUsername(String)
     */
    @Override
    @Cacheable(key = "'username-' + #username")
    public UserEntity findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    /**
     * @see UserManager#findByEmail(String)
     */
    @Override
    @Cacheable(key = "'email-' + #email")
    public UserEntity findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    /**
     * @see UserManager#findByMobile(String, String)
     */
    @Override
    @Cacheable(key = "'mobile-' + #mobileCountryCode + '-' + #mobile")
    public UserEntity findByMobile(String mobileCountryCode, String mobile) {
        return this.repository.findByMobileCountryCodeAndMobile(mobileCountryCode, mobile);
    }

    /**
     * @see UserManager#save(BaseEntity)
     */
    @Override
    @Caching(put = {
            @CachePut(key = "'id-' + #result.id"),
            @CachePut(key = "'username-' + #result.username"),
            @CachePut(key = "'email-' + #result.email"),
            @CachePut(key = "'mobile-' + #result.mobileCountryCode + '-' + #result.mobile"),
    })
    public UserEntity save(UserEntity entity) {
        return super.save(entity);
    }

    /**
     * @see UserManager#clearCache()
     */
    @Override
    @CacheEvict(beforeInvocation = true)
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("Clear cache [{}]...", SystemConstants.CACHE_USER);
        }
    }

}
