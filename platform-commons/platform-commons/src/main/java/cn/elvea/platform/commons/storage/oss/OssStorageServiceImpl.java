package cn.elvea.platform.commons.storage.oss;

import cn.elvea.platform.commons.storage.AbstractStorageService;
import cn.elvea.platform.commons.storage.StorageService;
import cn.elvea.platform.commons.storage.domain.FileObjectBuilder;
import cn.elvea.platform.commons.storage.domain.FileParameter;
import cn.elvea.platform.commons.storage.domain.dto.OssFileObject;
import cn.elvea.platform.commons.storage.enums.FileAccessType;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;

import java.io.InputStream;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see OssStorageService
 * @see StorageService
 * @since 0.0.1
 */
public class OssStorageServiceImpl extends AbstractStorageService<OSS> implements OssStorageService {

    private final OssStorageConfig config;

    /**
     * 构造函数
     *
     * @param config 存储设置
     */
    public OssStorageServiceImpl(OssStorageConfig config) {
        this.config = config;
    }

    /**
     * @see StorageService#getClient()
     */
    @Override
    public OSS getClient() {
        OSS oss = new OSSClientBuilder().build(this.config.getEndpoint(), this.config.getAccessKeyId(), this.config.getAccessKeySecret());
        if (oss.doesBucketExist(this.getBucketName())) {
            oss.createBucket(this.getBucketName());
        }
        return oss;
    }

    /**
     * @see StorageService#closeClient(Object)
     */
    @Override
    public void closeClient(OSS client) {
        client.shutdown();
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
        return this.config.getDomain();
    }

    public OssFileObject uploadFile(InputStream is, FileParameter params, String filename, String path) {
        OSS client = null;
        try {
            String key = "";
            client = getClient();
            client.putObject(this.getBucketName(), "", is);
            if (params != null && FileAccessType.PRIVATE.equals(params.getAccessType())) {
                client.setObjectAcl(this.getBucketName(), key, CannedAccessControlList.Private);
            } else {
                client.setObjectAcl(this.getBucketName(), key, CannedAccessControlList.PublicRead);
            }
            return FileObjectBuilder.ossStorageBuilder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
