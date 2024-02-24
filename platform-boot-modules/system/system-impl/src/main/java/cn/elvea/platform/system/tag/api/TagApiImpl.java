package cn.elvea.platform.system.tag.api;

import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.system.tag.model.converter.TagConverter;
import cn.elvea.platform.system.tag.model.entity.TagEntity;
import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.model.request.TagRelationRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cn.elvea.platform.system.tag.model.request.TagSearchRequest;
import cn.elvea.platform.system.tag.model.request.TagTypeRequest;
import cn.elvea.platform.system.tag.model.vo.TagRelationVo;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import cn.elvea.platform.system.tag.model.vo.TagVo;
import cn.elvea.platform.system.tag.service.TagRelationService;
import cn.elvea.platform.system.tag.service.TagService;
import cn.elvea.platform.system.tag.service.TagTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
     * @see TagApi#getTagType(TagTypeRequest)
     */
    @Override
    public TagTypeVo getTagType(TagTypeRequest request) {
        TagTypeVo vo = tagTypeService.getTagType(request.getType());
        if (vo != null && request.isWithItem()) {
            List<TagEntity> tagList = this.tagService.findByTypeId(vo.getId());
            if (CollectionUtils.isNotEmpty(tagList)) {
                vo.setItems(tagList.stream().map(TagConverter.INSTANCE::entity2Vo).toList());
            }
        }
        return vo;
    }

    /**
     * @see TagApi#search(TagSearchRequest)
     */
    @Override
    public Page<TagVo> search(TagSearchRequest request) {
        // 指定类型ID
        request.setTypeId(tagTypeService.getTagTypeId(request.getType()));

        Page<TagEntity> page = this.tagService.search(request);
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<TagVo> itemList = page.getContent().stream().map(TagConverter.INSTANCE::entity2Vo).toList();
            return new PageImpl<>(itemList, request.getPageable(), page.getTotalElements());
        }
        return new PageImpl<>(Collections.emptyList(), request.getPageable(), 0);
    }

    /**
     * @see TagApi#save(TagForm)
     */
    @Override
    public void save(TagForm form) {
        TagEntity entity = TagConverter.INSTANCE.form2Entity(form);
        this.tagService.save(entity);
    }

    /**
     * @see TagApi#getRelation(TagRelationRequest)
     */
    @Override
    public TagRelationVo getRelation(TagRelationRequest request) {
        TagRelationVo vo = TagRelationVo.builder().targetType(request.getTargetType()).targetId(request.getTargetId()).build();

        List<TagEntity> tagList = this.tagService.findByTarget(request);
        if (CollectionUtils.isNotEmpty(tagList)) {
            vo.setItems(tagList.stream().map(TagConverter.INSTANCE::entity2Vo).toList());
        }
        return vo;
    }

    /**
     * @see TagApi#saveRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveRelation(TagRelationSaveRequest request) {
        this.tagRelationService.saveRelation(request);
    }

}
