package cn.elvea.platform.system.tag.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagTypeService extends CachingEntityService<TagTypeEntity, Long> {

    /**
     * 获取指定标签类型
     */
    TagTypeVo getTagType(String code);

    /**
     * 获取指定编号的标签类型
     */
    TagTypeEntity findByCode(String code);

}
