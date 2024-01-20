package cn.elvea.platform.system.dict.api.impl;

import cn.elvea.platform.system.dict.api.DictApi;
import cn.elvea.platform.system.dict.model.converter.DictConverter;
import cn.elvea.platform.system.dict.model.entity.DictItemEntity;
import cn.elvea.platform.system.dict.model.form.DictForm;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;
import cn.elvea.platform.system.dict.service.DictItemService;
import cn.elvea.platform.system.dict.service.DictRelationService;
import cn.elvea.platform.system.dict.service.DictTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictApiImpl implements DictApi {

    private final DictItemService dictItemService;

    private final DictTypeService dictTypeService;

    private final DictRelationService dictRelationService;

    /**
     * @see DictApi#getDictType(String)
     */
    @Override
    public DictTypeVo getDictType(String code) {
        return dictTypeService.getDictType(code);
    }

    /**
     * @see DictApi#saveDict(DictForm)
     */
    @Override
    public void saveDict(DictForm form) {
        DictItemEntity entity = DictConverter.INSTANCE.form2Entity(form);
        this.dictItemService.save(entity);
    }

}
