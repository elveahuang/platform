package cn.elvea.platform.system.dict.api;

import cn.elvea.platform.system.dict.model.form.DictForm;
import cn.elvea.platform.system.dict.model.request.DictRelationRequest;
import cn.elvea.platform.system.dict.model.request.DictRelationSaveRequest;
import cn.elvea.platform.system.dict.model.request.DictSearchRequest;
import cn.elvea.platform.system.dict.model.request.DictTypeRequest;
import cn.elvea.platform.system.dict.model.vo.DictItemVo;
import cn.elvea.platform.system.dict.model.vo.DictRelationVo;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DictApi {

    /**
     * 获取字典类型定义
     */
    DictTypeVo getDictType(DictTypeRequest request);

    /**
     * 搜索字典
     */
    Page<DictItemVo> search(DictSearchRequest request);

    /**
     * 保存字典
     */
    void saveDict(DictForm form);

    /**
     * 获取目标实体关联的字典
     */
    DictRelationVo getDictRelation(DictRelationRequest request);

    /**
     * 保存目标实体和标签的关联
     */
    void saveDictRelation(DictRelationSaveRequest request);

}
