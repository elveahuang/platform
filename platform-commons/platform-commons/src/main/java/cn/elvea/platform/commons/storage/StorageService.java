package cn.elvea.platform.commons.storage;

/**
 * 存储服务
 *
 * @author elvea
 * @since 0.0.1
 */
public interface StorageService<C> {

    /**
     * 获取客户端
     */
    C getClient();

    /**
     * 关闭客户端
     */
    void closeClient(C client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
