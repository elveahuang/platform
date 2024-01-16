package cn.elvea.platform.commons.core.data.jpa.service;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.repository.BaseEntityRepository;
import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.commons.core.service.Service;

import java.io.Serializable;

/**
 * 增强实体服务接口
 *
 * @param <T> 实体类型
 * @param <K> 主键类型
 * @author elvea
 * @see EntityService
 * @see Service
 * @since 24.1.0
 */
public interface EnhancedEntityService<T extends IdEntity, K extends Serializable, R extends BaseEntityRepository<T, K>>
        extends EntityService<T, K> {

    R getRepository();

    Class<R> getRepositoryClass();

}
