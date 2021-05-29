package cn.elvea.platform.core.system.mapper;

import cn.elvea.platform.persistence.mybatis.page.PageList;
import cn.elvea.platform.core.system.domain.entity.EntityRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * EntityRelationMapper
 *
 * @author elvea
 * @since 0.0.1
 */
@Mapper
public interface EntityRelationMapper {

    /**
     * 分页搜索，不查询总记录数
     *
     * @param pageable {@link Pageable}
     * @return List
     */
    List<EntityRelationEntity> searchByPageable(Pageable pageable);

    /**
     * 分页搜索，查询总记录数
     *
     * @param pageable {@link Pageable}
     * @return List
     */
    PageList<EntityRelationEntity> searchByPage(Pageable pageable);

}
