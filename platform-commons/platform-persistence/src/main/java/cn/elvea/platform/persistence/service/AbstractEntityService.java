package cn.elvea.platform.persistence.service;

import cn.elvea.platform.base.domain.IdEntity;
import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 通用服务的抽象封装基础的操作
 *
 * @param <T> 实体
 * @param <K> 主键
 * @param <R> Repository
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractEntityService<T extends IdEntity, K extends Serializable, R extends JdbcRepository<T, K>>
        implements EntityService<T, K> {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected R repository;

    /**
     * @see EntityService#findById(Serializable)
     */
    @Override
    public T findById(K id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * @see EntityService#findByIds(Iterable)
     */
    @Override
    public Iterable<T> findByIds(Iterable<K> ids) {
        return this.repository.findAllById(ids);
    }

    /**
     * @see EntityService#findAll()
     */
    @Override
    public Iterable<T> findAll() {
        return this.repository.findAll();
    }

    /**
     * @see EntityService#findAll(Sort)
     */
    @Override
    public Iterable<T> findAll(Sort sort) {
        return this.repository.findAll(sort);
    }

    /**
     * @see EntityService#findAll(Pageable)
     */
    @Override
    public Iterable<T> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    /**
     * @see EntityService#save(IdEntity)
     */
    @Override
    public T save(T entity) {
        return this.repository.save(entity);
    }

    /**
     * @see EntityService#save(Iterable)
     */
    @Override
    public Iterable<T> save(Iterable<T> entities) {
        return this.repository.saveAll(entities);
    }

    /**
     * @see EntityService#delete(IdEntity)
     */
    @Override
    public void delete(T entity) {
        this.repository.delete(entity);
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(K id) {
        this.repository.deleteById(id);
    }

    /**
     * @see EntityService#deleteAll(Iterable)
     */
    @Override
    public void deleteAll(Iterable<T> entities) {
        this.repository.deleteAll(entities);
    }

    /**
     * @see EntityService#deleteAll()
     */
    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    /**
     * @see EntityService#count()
     */
    @Override
    public void count() {
        this.repository.count();
    }

    /**
     * @see EntityService#existsById(Serializable)
     */
    @Override
    public boolean existsById(K id) {
        return this.repository.existsById(id);
    }

}
