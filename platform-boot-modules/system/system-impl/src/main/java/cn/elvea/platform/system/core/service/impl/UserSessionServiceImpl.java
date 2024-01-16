package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity_;
import cn.elvea.platform.system.core.repository.UserSessionRepository;
import cn.elvea.platform.system.core.service.UserSessionService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see UserSessionService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Service
public class UserSessionServiceImpl extends BaseCachingEntityService<UserSessionEntity, Long, UserSessionRepository>
        implements UserSessionService {

    private final CacheKeyGenerator cacheKeyGenerator = SimpleCacheKeyGenerator.builder().prefix(SystemCacheConstants.USER_SESSION).build();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see UserSessionService#findBySessionId(String)
     */
    @Override
    public UserSessionEntity findBySessionId(String sessionId) {
        Specification<UserSessionEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(UserSessionEntity_.sessionId), sessionId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findOne(specification).orElse(null);
    }

}
