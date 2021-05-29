package cn.elvea.platform.core.attachment.service;

import cn.elvea.platform.commons.storage.StorageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttachmentService
 *
 * @author elvea
 * @see AttachmentService
 * @since 0.0.1
 */
@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService {

    StorageFactory storageFactory;

    /**
     * @see AttachmentService#uploadFile()
     */
    @Override
    public void uploadFile() {

    }

    @Autowired
    public void setStorageFactory(StorageFactory storageFactory) {
        this.storageFactory = storageFactory;
    }

}
