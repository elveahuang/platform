package cn.elvea.platform.commons.storage.min;

import lombok.Data;

import java.io.Serializable;

/**
 * MinIO配置参数
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class MinStorageConfig implements Serializable {
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
