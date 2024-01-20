package cn.elvea.platform.system.dict.api;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.system.commons.constants.SystemDictionaryConstants;
import cn.elvea.platform.system.dict.model.request.DictTypeRequest;
import cn.elvea.platform.system.dict.model.vo.DictTypeVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DictApiTests extends BaseTests {

    @Autowired
    DictApi dictApi;

    @Test
    public void baseTest() {
        DictTypeRequest request = DictTypeRequest.builder().type(SystemDictionaryConstants.BANNER).build();
        DictTypeVo vo = this.dictApi.getDictType(request);
        Assertions.assertNotNull(vo);
    }

}
