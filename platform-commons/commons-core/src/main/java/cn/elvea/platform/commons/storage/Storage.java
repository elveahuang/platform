package cn.elvea.platform.commons.storage;

import cn.elvea.platform.commons.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.storage.cos.CosStorageService;
import cn.elvea.platform.commons.storage.cos.CosStorageServiceImpl;
import cn.elvea.platform.commons.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.storage.min.MinStorageService;
import cn.elvea.platform.commons.storage.min.MinStorageServiceImpl;
import cn.elvea.platform.commons.storage.oss.OssStorageConfig;
import cn.elvea.platform.commons.storage.oss.OssStorageService;
import cn.elvea.platform.commons.storage.oss.OssStorageServiceImpl;

/**
 * @author elvea
 * @since 24.1.0
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
