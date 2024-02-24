package cn.elvea.platform.system.core.mapper;

import cn.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cn.elvea.platform.system.core.model.entity.EntityRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 * @see BaseEntityMapper
 * @since 24.1.0
 */
@Mapper
@Repository
public interface EntityRelationMapper extends BaseEntityMapper<EntityRelationEntity, Long> {
}
