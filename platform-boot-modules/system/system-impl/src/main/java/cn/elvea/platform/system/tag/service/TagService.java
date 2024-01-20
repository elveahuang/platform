package cn.elvea.platform.system.tag.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagService extends CachingEntityService<TagEntity, Long> {

    /**
     * 获取指定标签类型下属所有标签
     */
    List<TagEntity> findByTypeId(Long tagTypeId);

}
