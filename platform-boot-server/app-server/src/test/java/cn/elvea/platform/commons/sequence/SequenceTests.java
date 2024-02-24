package cn.elvea.platform.commons.sequence;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.sequence.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IdWorkerTests
 *
 * @author elvea
 * @since 24.1.0
 */
public class SequenceTests extends BaseTests {

    @Autowired
    private Sequence sequence;

    @Test
    public void test() {
        Assertions.assertNotNull(this.sequence);
        Assertions.assertTrue(this.sequence.nextId() > 0);
    }

}
