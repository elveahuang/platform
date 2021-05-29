package cn.elvea.platform.commons.storage;

import cn.elvea.platform.commons.storage.cos.CosStorageService;
import cn.elvea.platform.commons.storage.min.MinStorageService;
import cn.elvea.platform.commons.storage.oss.OssStorageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * StorageFactory
 *
 * @author elvea
 * @see StorageFactory
 * @since 0.0.1
 */
public class StorageFactoryImpl implements StorageFactory, ApplicationContextAware {

    private ApplicationContext context;

    private final StorageConfig config;

    public StorageFactoryImpl(StorageConfig config) {
        this.config = config;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    public StorageService<?> getStorageService() {
        return null;
    }

    @Override
    public MinStorageService getMinStorageService() {
        return this.context.getBean(MinStorageService.class);
    }

    @Override
    public OssStorageService getOssStorageService() {
        return this.context.getBean(OssStorageService.class);
    }

    @Override
    public CosStorageService getCosStorageService() {
        return this.context.getBean(CosStorageService.class);
    }

}
