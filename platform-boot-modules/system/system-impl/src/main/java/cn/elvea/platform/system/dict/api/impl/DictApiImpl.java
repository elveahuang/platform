package cn.elvea.platform.system.dict.api.impl;

import cn.elvea.platform.system.dict.api.DictApi;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;
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

    private final DictTypeService dictTypeService;

    @Override
    public DictTypeVo getDictType(String code) {
        return dictTypeService.getDictType(code);
    }

}
