package cn.elvea.platform.commons.service.support;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProxyServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class ProxySelfServiceTests extends CommonsTestApplicationTests {

    @Autowired
    Context context;

    @Autowired
    TestProxySelfService testProxyService;

    @Test
    public void test() {
        TestProxySelfService service = this.testProxyService.getInstance();
        System.out.println(service);
        System.out.println(this.testProxyService);
        Assertions.assertNotNull(service);
    }

}
