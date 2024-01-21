package cn.elvea.platform.system.tag.api;

import cn.elvea.platform.system.tag.model.form.TagForm;
import cn.elvea.platform.system.tag.model.request.TagRelationRequest;
import cn.elvea.platform.system.tag.model.request.TagRelationSaveRequest;
import cn.elvea.platform.system.tag.model.request.TagSearchRequest;
import cn.elvea.platform.system.tag.model.request.TagTypeRequest;
import cn.elvea.platform.system.tag.model.vo.TagRelationVo;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import cn.elvea.platform.system.tag.model.vo.TagVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagApi {

    /**
     * 获取标签类型
     */
    TagTypeVo getTagType(TagTypeRequest request);

    /**
     * 搜索标签
     */
    Page<TagVo> search(TagSearchRequest request);

    /**
     * 保存标签
     */
    void save(TagForm form);

    /**
     * 获取实体关联
     */
    TagRelationVo getRelation(TagRelationRequest request);

    /**
     * 保存实体关联
     */
    void saveRelation(TagRelationSaveRequest request);

}
