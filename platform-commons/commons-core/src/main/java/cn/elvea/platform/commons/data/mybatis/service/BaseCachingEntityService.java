package cn.elvea.platform.commons.data.mybatis.service;

import cn.elvea.platform.commons.cache.service.CacheService;
import cn.elvea.platform.commons.data.domain.IdEntity;
import cn.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.commons.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 * @see BaseEntityService
 * @since 24.1.0
 */
@Slf4j
@NoRepositoryBean
@Transactional
public abstract class BaseCachingEntityService<T extends IdEntity, K extends Serializable, M extends BaseEntityMapper<T, K>>
        extends BaseEntityService<T, K, M> implements CachingEntityService<T, K> {

    private CacheService cacheService;

    /**
     * @see CachingEntityService#save(IdEntity)
     */
    @Override
    public T save(T entity) {
        T t = super.save(entity);
        deleteCache(t);
        return t;
    }

    /**
     * @see CachingEntityService#insert(IdEntity)
     */
    @Override
    public T insert(T entity) {
        T t = super.insert(entity);
        setCache(t);
        return t;
    }

    /**
     * @see CachingEntityService#updateById(IdEntity)
     */
    @Override
    public T updateById(T entity) {
        T t = super.updateById(entity);
        deleteCache(t);
        return t;
    }

    /**
     * @see CachingEntityService#saveBatch(Collection, int)
     */
    @Override
    public void saveBatch(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isNotEmpty(entityList)) {
            super.saveBatch(entityList, batchSize);
            entityList.forEach(this::deleteCache);
        }
    }

    /**
     * @see CachingEntityService#getCacheService()
     */
    @Override
    public CacheService getCacheService() {
        return this.cacheService;
    }

    @Autowired
    @Qualifier("cacheService")
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

}
