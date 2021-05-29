package cn.elvea.platform.commons.storage.cos;

import lombok.Data;

import java.io.Serializable;

/**
 * 腾讯云对象存储配置参数
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class CosStorageConfig implements Serializable {
    /**
     * Endpoint
     */
    private String endpoint = "";
    /**
     * Access Key
     */
    private String accessKey = "";
    /**
     * Secret Key
     */
    private String secretKey = "";
    /**
     * BucketName
     */
    private String bucketName = "";
}
