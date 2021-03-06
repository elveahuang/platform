package cn.elvea.platform.commons.storage.oss;

import lombok.Data;

import java.io.Serializable;

/**
 * 阿里云存储配置参数
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class OssStorageConfig implements Serializable {
    /**
     * Endpoint
     */
    private String endpoint = "";
    /**
     * Access Key Id
     */
    private String accessKeyId = "";
    /**
     * Access Key Secret
     */
    private String accessKeySecret = "";
    /**
     * Bucket Name
     */
    private String bucketName = "";
    /**
     * 自定义域名
     */
    private String domain = "";
}
