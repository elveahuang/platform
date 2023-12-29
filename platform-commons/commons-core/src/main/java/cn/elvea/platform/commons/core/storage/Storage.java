package cn.elvea.platform.commons.core.storage;

import cn.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.core.storage.cos.CosStorageService;
import cn.elvea.platform.commons.core.storage.cos.CosStorageServiceImpl;
import cn.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.core.storage.min.MinStorageService;
import cn.elvea.platform.commons.core.storage.min.MinStorageServiceImpl;
import cn.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cn.elvea.platform.commons.core.storage.oss.OssStorageService;
import cn.elvea.platform.commons.core.storage.oss.OssStorageServiceImpl;

/**
 * @author elvea
 * @since 0.0.1
 */
public class Storage {

    private final StorageConfig config;

    public Storage(StorageConfig config) {
        this.config = config;
    }

    public StorageService getStorageService() {
        return switch (this.config.getType()) {
            case COS -> getCosStorageService();
            case OSS -> getOssStorageService();
            default -> getMinStorageService();
        };
    }

    public MinStorageService getMinStorageService() {
        return this.getMinStorageService(this.config.getMin());
    }

    public MinStorageService getMinStorageService(MinStorageConfig config) {
        return new MinStorageServiceImpl(config);
    }

    public OssStorageService getOssStorageService() {
        return this.getOssStorageService(this.config.getOss());
    }

    public OssStorageService getOssStorageService(OssStorageConfig config) {
        return new OssStorageServiceImpl(config);
    }

    public CosStorageService getCosStorageService() {
        return this.getCosStorageService(this.config.getCos());
    }

    public CosStorageService getCosStorageService(CosStorageConfig config) {
        return new CosStorageServiceImpl(config);
    }

}
