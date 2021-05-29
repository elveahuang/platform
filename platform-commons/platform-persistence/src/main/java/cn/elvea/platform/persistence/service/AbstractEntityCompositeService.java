package cn.elvea.platform.persistence.service;

import cn.elvea.platform.base.domain.IdEntity;
import cn.elvea.platform.persistence.jdbc.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * CompositeService
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractEntityCompositeService<T extends IdEntity, K extends Serializable, R extends JdbcRepository<T, K>, M>
        extends AbstractEntityService<T, K, R> {

    @Autowired
    protected M mapper;

}
