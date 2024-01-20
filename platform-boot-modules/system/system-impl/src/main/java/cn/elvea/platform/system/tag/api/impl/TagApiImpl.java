package cn.elvea.platform.system.tag.api.impl;

import cn.elvea.platform.system.tag.api.TagApi;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import cn.elvea.platform.system.tag.service.TagTypeService;
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
public class TagApiImpl implements TagApi {

    private final TagTypeService tagTypeService;

    @Override
    public TagTypeVo getTagType(String code) {
        return tagTypeService.getTagType(code);
    }

}
