package cn.elvea.platform.system.dict.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.dict.model.entity.DictRelationEntity;
import cn.elvea.platform.system.dict.model.request.DictRelationDeleteRequest;
import cn.elvea.platform.system.dict.model.request.DictRelationRequest;
import cn.elvea.platform.system.dict.model.request.DictRelationSaveRequest;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DictRelationService extends CachingEntityService<DictRelationEntity, Long> {

    /**
     * 查询目标实体关联信息
     */
    List<DictRelationEntity> findRelations(DictRelationRequest request);

    /**
     * 保存目标实体关联信息
     */
    void saveRelation(DictRelationSaveRequest request);

    /**
     * 删除目标实体关联
     */
    void deleteRelation(DictRelationRequest request);

    /**
     * 删除关联信息
     */
    void deleteRelation(DictRelationDeleteRequest request);

}
