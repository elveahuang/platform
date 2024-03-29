package cn.elvea.platform.commons.utils;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.context.Context;
import cn.elvea.platform.commons.sequence.Sequence;
import cn.elvea.platform.commons.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class SpringUtilsTests extends BaseTests {

    @Test
    public void test() {
        Object context = SpringUtils.getBean("context");
        Assertions.assertNotNull(context);

        context = SpringUtils.getBean("context", Context.class);
        Assertions.assertNotNull(context);

        Assertions.assertTrue(SpringUtils.containsBean("context"));
        Assertions.assertTrue(SpringUtils.isSingleton("context"));
        Assertions.assertTrue(ClassUtils.isAssignable(SpringUtils.getType("context"), Context.class));

        Sequence sequence = SpringUtils.getBean(Sequence.class);
        Assertions.assertNotNull(sequence);
    }

}
