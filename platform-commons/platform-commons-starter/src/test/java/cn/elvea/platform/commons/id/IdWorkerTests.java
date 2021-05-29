package cn.elvea.platform.commons.id;

import cn.elvea.platform.commons.id.impl.IdWork;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * IdWorkerTests
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IdWorkerTests {

    @Autowired
    IdWorker idWorker;

    @Test
    public void test() {
        Assertions.assertNotNull(this.idWorker);
    }

    @Test
    public void snowflakeTest() {
        IdWorker snowflake = new IdWork(1, 1);
        for (int i = 0; i <= 1000; i++) {
            System.out.println(snowflake.nextId());
        }
    }

}
