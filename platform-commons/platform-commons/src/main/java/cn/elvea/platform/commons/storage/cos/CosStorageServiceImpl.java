package cn.elvea.platform.commons.storage.cos;

import cn.elvea.platform.commons.storage.AbstractStorageService;
import cn.elvea.platform.commons.storage.StorageService;
import cn.elvea.platform.commons.storage.domain.FileObjectBuilder;
import cn.elvea.platform.commons.storage.domain.FileParameter;
import cn.elvea.platform.commons.storage.domain.dto.CosFileObject;
import cn.elvea.platform.commons.storage.enums.FileAccessType;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.region.Region;

import java.io.InputStream;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see CosStorageService
 * @see StorageService
 * @since 0.0.1
 */
public class CosStorageServiceImpl extends AbstractStorageService<COSClient> implements CosStorageService {

    private final CosStorageConfig config;

    /**
     * 构造函数
     *
     * @param config 存储设置
     */
    public CosStorageServiceImpl(CosStorageConfig config) {
        this.config = config;
    }

    /**
     * @see StorageService#getClient()
     */
    @Override
    public COSClient getClient() {
        COSCredentials credentials = new BasicCOSCredentials(this.config.getAccessKey(), this.config.getSecretKey());
        Region region = new Region(this.config.getEndpoint());
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(credentials, clientConfig);
    }

    /**
     * @see StorageService#closeClient(Object)
     */
    @Override
    public void closeClient(COSClient client) {
        if (client != null) {
            client.shutdown();
        }
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

    public CosFileObject uploadFile(InputStream is, FileParameter params, String filename, String path) {
        COSClient client = null;
        try {
            String key = "";
            client = this.getClient();
            client.putObject(getBucketName(), key, is, null);
            if (params != null && FileAccessType.PRIVATE.equals(params.getAccessType())) {
                client.setObjectAcl(this.getBucketName(), key, CannedAccessControlList.Private);
            } else {
                client.setObjectAcl(this.getBucketName(), key, CannedAccessControlList.PublicRead);
            }
            return FileObjectBuilder.cosStorageBuilder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
