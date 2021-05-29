package cn.elvea.platform.commons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ContextTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class ContextTests extends CommonsTestApplicationTests {

    @Autowired
    private Context context;

    @Test
    public void test() {
        Assertions.assertNotNull(context);
    }

}
