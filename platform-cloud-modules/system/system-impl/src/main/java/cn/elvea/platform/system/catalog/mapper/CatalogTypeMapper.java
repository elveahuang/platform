package cn.elvea.platform.system.catalog.mapper;

import cn.elvea.platform.commons.core.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.catalog.model.entity.CatalogTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
@Repository
public interface CatalogTypeMapper extends BaseEntityMapper<CatalogTypeEntity, Long> {
}
