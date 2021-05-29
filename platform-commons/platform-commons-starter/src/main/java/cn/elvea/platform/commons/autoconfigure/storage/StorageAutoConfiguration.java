package cn.elvea.platform.commons.autoconfigure.storage;

import cn.elvea.platform.commons.storage.StorageFactory;
import cn.elvea.platform.commons.storage.StorageFactoryImpl;
import cn.elvea.platform.commons.storage.cos.CosStorageService;
import cn.elvea.platform.commons.storage.cos.CosStorageServiceImpl;
import cn.elvea.platform.commons.storage.min.MinStorageService;
import cn.elvea.platform.commons.storage.min.MinStorageServiceImpl;
import cn.elvea.platform.commons.storage.oss.OssStorageService;
import cn.elvea.platform.commons.storage.oss.OssStorageServiceImpl;
import com.aliyun.oss.OSS;
import com.qcloud.cos.COSClient;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * StorageAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(StorageConfigProperties.class)
public class StorageAutoConfiguration {

    private final StorageConfigProperties config;

    public StorageAutoConfiguration(StorageConfigProperties config) {
        log.debug("current storage is {}", config.getType());
        this.config = config;
    }

    /**
     * CosStorageService
     *
     * @return {@link CosStorageService}
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass({COSClient.class})
    CosStorageService cosStorageService() {
        return new CosStorageServiceImpl(config.getCos());
    }

    /**
     * MinStorageService
     *
     * @return {@link MinStorageService}
     */
    @Bean
    @ConditionalOnClass({MinioClient.class})
    @ConditionalOnMissingBean
    MinStorageService minStorageService() {
        return new MinStorageServiceImpl(config.getMin());
    }

    /**
     * OssStorageService
     *
     * @return {@link OssStorageService}
     */
    @Bean
    @ConditionalOnClass({OSS.class})
    @ConditionalOnMissingBean
    OssStorageService ossStorageService() {
        return new OssStorageServiceImpl(config.getOss());
    }

    /**
     * StorageFactory
     *
     * @return {@link StorageFactory}
     */
    @Bean
    public StorageFactory storageFactory() {
        return new StorageFactoryImpl(config);
    }

}
