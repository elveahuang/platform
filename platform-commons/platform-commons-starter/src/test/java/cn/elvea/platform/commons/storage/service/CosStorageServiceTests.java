package cn.elvea.platform.commons.storage.service;

import cn.elvea.platform.commons.storage.cos.CosStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * CosStorageServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CosStorageServiceTests {

    @Autowired(required = false)
    CosStorageService cosStorageService;

    @Test
    public void baseTest() {
    }

}
