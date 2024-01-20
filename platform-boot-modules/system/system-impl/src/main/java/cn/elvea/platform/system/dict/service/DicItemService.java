package cn.elvea.platform.system.dict.service;

import cn.elvea.platform.commons.core.data.jpa.service.EnhancedEntityService;
import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.dict.model.entity.DictItemEntity;
import cn.elvea.platform.system.dict.repository.DictItemRepository;
import cn.elvea.platform.system.tag.model.form.TagForm;

/**
 * @author elvea
 */
public interface DicItemService extends CachingEntityService<DictItemEntity, Long>, EnhancedEntityService<DictItemEntity, Long, DictItemRepository> {

    /**
     * 保存标签
     */
    void saveTag(TagForm form);

    /**
     * 根据标签类型id删除相关联的标签
     */
    void deleteTagById(Long id);

}
