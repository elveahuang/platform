package cn.elvea.platform.commons.core.data.mybatis.service;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.commons.core.service.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 增强实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see EntityService
 * @see Service
 * @since 0.0.1
 */
public interface EnhancedEntityService<T extends IdEntity, K extends Serializable, M extends BaseEntityMapper<T, K>>
        extends EntityService<T, K> {

    M getMapper();

    Class<M> getMapperClass();

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    List<T> findAll(Page<T> page);

    /**
     * 查询所有记录，支持分页
     *
     * @return Iterable<T>
     */
    Page<T> findByPage(Page<T> page);

}
