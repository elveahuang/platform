package cn.elvea.platform.system.tag.api;

import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.model.request.TagRelationRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagApi {

    TagTypeVo getTagType(String code);

    TagTypeVo getTagType(String code, boolean withTag);

    void saveTag(TagForm form);

    void getTagRelation(TagRelationRequest request);

    void saveTagRelation(TagRelationSaveRequest request);

}
