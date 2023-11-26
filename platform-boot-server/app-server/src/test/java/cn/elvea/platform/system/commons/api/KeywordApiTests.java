package cn.elvea.platform.system.commons.api;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
public class KeywordApiTests extends BaseTests {

    @Autowired
    KeywordApi keywordApi;

    @Test
    public void baseTest() {
        keywordApi.initialize();
    }

}
