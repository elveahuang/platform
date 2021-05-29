package cn.elvea.platform.commons.storage;

import cn.elvea.platform.commons.storage.cos.CosStorageService;
import cn.elvea.platform.commons.storage.min.MinStorageService;
import cn.elvea.platform.commons.storage.oss.OssStorageService;

/**
 * StorageFactory
 *
 * @author elvea
 * @since 0.0.1
 */
public interface StorageFactory {

    /**
     * 获取存储服务
     *
     * @return {@link StorageService}
     */
    StorageService<?> getStorageService();

    /**
     * 获取存储服务
     *
     * @return {@link MinStorageService}
     */
    MinStorageService getMinStorageService();

    /**
     * 获取存储服务
     *
     * @return {@link OssStorageService}
     */
    OssStorageService getOssStorageService();

    /**
     * 获取存储服务
     *
     * @return {@link CosStorageService}
     */
    CosStorageService getCosStorageService();

}
