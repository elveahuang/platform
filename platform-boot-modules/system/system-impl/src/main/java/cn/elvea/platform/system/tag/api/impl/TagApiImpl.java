package cn.elvea.platform.system.tag.api.impl;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.system.tag.api.TagApi;
import cn.elvea.platform.system.tag.model.converter.TagConverter;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.model.request.TagRelationRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import cn.elvea.platform.system.tag.service.TagRelationService;
import cn.elvea.platform.system.tag.service.TagService;
import cn.elvea.platform.system.tag.service.TagTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TagApiImpl implements TagApi {

    private final TagService tagService;

    private final TagTypeService tagTypeService;

    private final TagRelationService tagRelationService;

    /**
     * @see TagApi#getTagType(String)
     */
    @Override
    public TagTypeVo getTagType(String code) {
        return this.getTagType(code, false);
    }

    /**
     * @see TagApi#getTagType(String, boolean)
     */
    @Override
    public TagTypeVo getTagType(String code, boolean withTag) {
        TagTypeVo vo = tagTypeService.getTagType(code);
        if (vo != null && withTag) {
            List<TagEntity> tagList = this.tagService.findByTypeId(vo.getId());
            if (CollectionUtils.isNotEmpty(tagList)) {
                vo.setItems(tagList.stream().map(TagConverter.INSTANCE::entity2Vo).toList());
            }
        }
        return vo;
    }

    /**
     * @see TagApi#saveTag(TagForm)
     */
    @Override
    public void saveTag(TagForm form) {
        TagEntity entity = TagConverter.INSTANCE.form2Entity(form);
        this.tagService.save(entity);
    }

    /**
     * @see TagApi#getTagRelation(TagRelationRequest)
     */
    @Override
    public void getTagRelation(TagRelationRequest request) {
    }

    /**
     * @see TagApi#saveTagRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveTagRelation(TagRelationSaveRequest request) {
        this.tagRelationService.saveRelation(request);
    }

}
