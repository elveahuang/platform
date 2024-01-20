package cn.elvea.platform.system.tag.api;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.system.commons.constants.SystemTagConstants;
import cn.elvea.platform.system.tag.model.vo.TagTypeVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class TagApiTests extends BaseTests {

    @Autowired
    TagApi tagApi;

    @Test
    public void baseTest() {
        TagTypeVo vo = this.tagApi.getTagType(SystemTagConstants.SYSTEM);
        Assertions.assertNotNull(vo);
    }

}
