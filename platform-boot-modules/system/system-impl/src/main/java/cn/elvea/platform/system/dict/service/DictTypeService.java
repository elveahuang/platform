package cn.elvea.platform.system.dict.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.dict.model.entity.DictTypeEntity;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DictTypeService extends CachingEntityService<DictTypeEntity, Long> {

    /**
     * 获取指定字典类型
     */
    DictTypeVo getDictType(String code);

    /**
     * 获取指定编号的字典类型
     */
    DictTypeEntity findByCode(String code);

}
