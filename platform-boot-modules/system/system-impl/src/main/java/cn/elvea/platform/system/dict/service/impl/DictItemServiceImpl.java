package cn.elvea.platform.system.dict.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.dict.model.entity.DictItemEntity;
import cn.elvea.platform.system.dict.repository.DictItemRepository;
import cn.elvea.platform.system.dict.service.DictRelationService;
import cn.elvea.platform.system.dict.service.DicItemService;
import cn.elvea.platform.system.tag.model.form.TagForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**¬ø
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictItemServiceImpl extends BaseCachingEntityService<DictItemEntity, Long, DictItemRepository> implements DicItemService {

    private final DictRelationService relationService;

    @Override
    public void saveTag(TagForm form) {
    }

    public void deleteById(Long tagId) {
        DictItemEntity tagEntity = this.findById(tagId);
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
