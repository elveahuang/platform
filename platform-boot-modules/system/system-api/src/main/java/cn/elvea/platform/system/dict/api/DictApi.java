package cn.elvea.platform.system.dict.api;

import cn.elvea.platform.system.dict.model.vo.DictTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DictApi {

    /**
     * 获取字典类型
     */
    DictTypeVo getDictType(String code);

}
