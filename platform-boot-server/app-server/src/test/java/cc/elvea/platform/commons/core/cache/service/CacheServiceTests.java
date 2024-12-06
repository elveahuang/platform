package cc.elvea.platform.commons.core.cache.service;

import cc.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public class CacheServiceTests extends BaseTests {

    @Autowired
    CacheService cacheService;

    public void base() throws Exception {
        Assertions.assertNotNull(this.cacheService);
    }

}
