package cn.elvea.platform.system.dict.api;

import cn.elvea.platform.system.dict.model.form.DictForm;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DictApi {

    DictTypeVo getDictType(String code);

    void saveDict(DictForm form);

}
