package cn.elvea.platform.commons.core.storage.service;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.sequence.Sequence;
import cn.elvea.platform.commons.core.storage.StorageManager;
import cn.elvea.platform.commons.core.storage.min.MinStorageService;
import cn.elvea.platform.commons.core.storage.oss.OssStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 * @since 0.0.1
 */
public class StorageServiceTests extends BaseTests {

    @Autowired()
    Sequence sequence;

    @Autowired(required = false)
    StorageManager storageManager;

    @Test
    public void minStorageServiceTest() throws Exception {
        MinStorageService service = this.storageManager.getMinStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        service.uploadFile(resource.getFile());
    }

    @Test
    public void ossStorageServiceTest() throws Exception {
        OssStorageService service = this.storageManager.getOssStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        service.uploadFile(resource.getFile());
    }

}
