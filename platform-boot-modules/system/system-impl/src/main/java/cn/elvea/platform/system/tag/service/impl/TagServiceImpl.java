package cn.elvea.platform.system.tag.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.repository.TagRepository;
import cn.elvea.platform.system.tag.service.TagRelationService;
import cn.elvea.platform.system.tag.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**¬ø
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl extends BaseCachingEntityService<TagEntity, Long, TagRepository> implements TagService {

    private final TagRelationService relationService;

    @Override
    public void saveTag(TagForm form) {
    }

    public void deleteById(Long tagId) {
        TagEntity tagEntity = this.findById(tagId);
        if (!ObjectUtils.isEmpty(tagEntity)) {
            tagEntity.setActive(Boolean.FALSE);
            this.save(tagEntity);
            relationService.deleteByTagId(tagEntity.getId(), tagEntity.getTagTypeId());

        }
    }


    @Override
    public void deleteTagById(Long tagTypeId) {
    }

}
