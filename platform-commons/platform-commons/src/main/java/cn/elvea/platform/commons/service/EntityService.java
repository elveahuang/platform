package cn.elvea.platform.commons.service;

import cn.elvea.platform.base.domain.IdEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * 通用实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @since 0.0.1
 */
public interface EntityService<T extends IdEntity, K extends Serializable> extends Service {

    /**
     * 根据ID查询唯一记录
     *
     * @param id ID
     * @return T
     */
    T findById(K id);

    /**
     * 根据ID查询多条记录
     *
     * @param ids ID
     * @return Iterable<T>
     */
    Iterable<T> findByIds(Iterable<K> ids);

    /**
     * 查询所有记录
     *
     * @return Iterable<T>
     */
    Iterable<T> findAll();

    /**
     * 查询所有记录，支持排序
     *
     * @return Iterable<T>
     */
    Iterable<T> findAll(Sort sort);

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    Iterable<T> findAll(Pageable pageable);

    /**
     * 保存实体
     *
     * @return T
     */
    T save(T entity);

    /**
     * 批量保存实体
     *
     * @return Iterable<T>
     */
    Iterable<T> save(Iterable<T> entities);

    /**
     * 删除单个实体
     */
    void delete(T entity);

    /**
     * 删除单个实体
     */
    void deleteById(K id);

    /**
     * 删除多个实体
     */
    void deleteAll(Iterable<T> entities);

    /**
     * 删除全部实体
     */
    void deleteAll();

    /**
     * 查询记录总数
     */
    void count();

    /**
     * 查询记录总数
     */
    boolean existsById(K id);

}
