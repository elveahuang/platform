package cn.elvea.platform.system.tag.service;

import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.repository.TagRepository;

/**
 * @author elvea
 */
public interface TagService extends CachingEntityService<TagEntity, Long>, EnhancedEntityService<TagEntity, Long, TagRepository> {

    /**
     * 保存标签
     */
    void saveTag(TagForm form);

    /**
     * 根据标签类型id删除相关联的标签
     */
    void deleteTagById(Long id);

}
