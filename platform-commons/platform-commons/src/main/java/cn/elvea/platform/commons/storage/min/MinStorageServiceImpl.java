package cn.elvea.platform.commons.storage.min;

import cn.elvea.platform.commons.storage.AbstractStorageService;
import cn.elvea.platform.commons.storage.StorageService;
import cn.elvea.platform.commons.storage.domain.FileObjectBuilder;
import cn.elvea.platform.commons.storage.domain.FileParameter;
import cn.elvea.platform.commons.storage.domain.dto.MinFileObject;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;

import java.io.InputStream;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see MinStorageService
 * @see StorageService
 * @since 0.0.1
 */
public class MinStorageServiceImpl extends AbstractStorageService<MinioClient> implements MinStorageService {

    private final MinStorageConfig config;

    /**
     * 构造函数
     *
     * @param config 存储设置
     */
    public MinStorageServiceImpl(MinStorageConfig config) {
        this.config = config;
    }

    /**
     * @see StorageService#getClient()
     */
    @Override
    public MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(this.config.getEndpoint())
                .credentials(this.config.getAccessKey(), this.config.getSecretKey())
                .build();
    }

    /**
     * @see StorageService#closeClient(Object)
     */
    @Override
    public void closeClient(MinioClient client) {
    }

    /**
     * @see StorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see StorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return null;
    }

    public MinFileObject uploadFile(InputStream is, FileParameter params, String filename, String path) throws Exception {
        MinioClient client = null;
        try {
            String key = "";
            client = getClient();
            ObjectWriteResponse response = client.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(getBucketName())
                            .object("my-objectname")
                            .filename("my-video.avi")
                            .contentType("video/mp4")
                            .build());
            return FileObjectBuilder.minStorageBuilder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
