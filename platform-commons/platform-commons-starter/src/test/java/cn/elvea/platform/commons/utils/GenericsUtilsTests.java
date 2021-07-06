package cn.elvea.platform.commons.utils;

import cn.elvea.platform.commons.service.ProxySelfService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * GenericsUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class GenericsUtilsTests {

    @Test
    public void test() {
        Class<?> clazz01 = GenericsUtils.getSuperGenericType(TestSelfService.class, BaseService.class, 0);
        Class<?> clazz02 = GenericsUtils.getSuperGenericType(TestSelfService.class, BaseService.class, 1);
        Assertions.assertEquals(clazz01, Long.class);
        Assertions.assertEquals(clazz02, String.class);
        Class<?> clazz03 = GenericsUtils.getSuperGenericType(TestSelfService.class, AbstractService.class, 0);
        Class<?> clazz04 = GenericsUtils.getSuperGenericType(TestSelfService.class, AbstractService.class, 1);
        Assertions.assertEquals(clazz03, Long.class);
        Assertions.assertEquals(clazz04, String.class);
        Class<?> clazz05 = GenericsUtils.getSuperGenericType(TestSelfService.class, ProxySelfService.class, 0);
        Assertions.assertEquals(clazz05, TestSelfService.class);
        Class<?> clazz06 = GenericsUtils.getSuperGenericType(TestSelfService.class, 0);
        Class<?> clazz07 = GenericsUtils.getSuperGenericType(TestSelfService.class, 1);
        Assertions.assertEquals(clazz06, Long.class);
        Assertions.assertEquals(clazz07, String.class);

        Class<?> clazz11 = GenericsUtils.getSuperInterfacesGenericType(TestSelfService.class, BaseService.class, 0);
        Class<?> clazz12 = GenericsUtils.getSuperInterfacesGenericType(TestSelfService.class, BaseService.class, 1);
        Assertions.assertEquals(clazz11, Long.class);
        Assertions.assertEquals(clazz12, String.class);
        Class<?> clazz13 = GenericsUtils.getSuperInterfacesGenericType(TestSelfService.class, ProxySelfService.class, 0);
        Assertions.assertEquals(clazz13, TestSelfService.class);
        Class<?> clazz14 = GenericsUtils.getSuperClassGenericType(TestSelfService.class, 0);
        Class<?> clazz15 = GenericsUtils.getSuperClassGenericType(TestSelfService.class, 1);
        Assertions.assertEquals(clazz14, Long.class);
        Assertions.assertEquals(clazz15, String.class);
    }

    interface BaseService<T, K> {
    }

    static abstract class AbstractService<T, K> {
    }

    static class TestSelfService
            extends AbstractService<Long, String>
            implements ProxySelfService<TestSelfService>, BaseService<Long, String> {
    }

}
