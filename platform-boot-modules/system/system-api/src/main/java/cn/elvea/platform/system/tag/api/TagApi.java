package cn.elvea.platform.system.tag.api;

import cn.elvea.platform.system.tag.model.vo.TagTypeVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TagApi {

    TagTypeVo getTagType(String code);

}
