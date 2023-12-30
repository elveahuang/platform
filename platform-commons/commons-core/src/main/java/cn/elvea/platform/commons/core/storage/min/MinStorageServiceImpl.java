package cn.elvea.platform.commons.core.storage.min;

import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.StorageUtils;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import cn.elvea.platform.commons.core.utils.JacksonUtils;
import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author elvea
 * @see MinStorageService
 * @see StorageService
 * @since 0.0.1
 */
@Slf4j
public class MinStorageServiceImpl implements MinStorageService, StorageService {

    private final MinStorageConfig config;

    public MinStorageServiceImpl(MinStorageConfig config) {
        this.config = config;
    }

    /**
     * @see MinStorageService#getClient()
     */
    @Override
    public MinioClient getClient() {
        return MinioClient.builder().endpoint(this.config.getEndpoint()).credentials(this.config.getAccessKey(), this.config.getSecretKey()).build();
    }

    /**
     * @see MinStorageService#closeClient(MinioClient)
     */
    @Override
    public void closeClient(MinioClient client) {
    }

    /**
     * @see MinStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see MinStorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return this.config.getDomain();
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String key, boolean withLocalFile) {
        MinioClient client = null;
        try {
            client = getClient();

            GetObjectArgs args = GetObjectArgs.builder().bucket(getBucketName()).object(key).object(key).build();

            try (GetObjectResponse response = client.getObject(args)) {
                log.error("Minio getObject response - [{}].", response.object());

                // 获取文件链接
                String url;
                if (StrUtil.isBlank(getDomain())) {
                    GetPresignedObjectUrlArgs urlArgs = GetPresignedObjectUrlArgs.builder().bucket(getBucketName()).object(key).method(Method.GET).build();
                    url = client.getPresignedObjectUrl(urlArgs);
                    log.error("Minio getObjectUrl response - [{}].", url);
                    url = url.substring(0, url.indexOf("?"));
                } else {
                    url = getDomain() + "/" + key;
                }
                log.error("Minio getObjectUrl - [{}].", url);

                // 创建本地临时目录文件
                File localTempFile = null;
                if (withLocalFile) {
                    localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(key));
                    try (InputStream is = new FileInputStream(localTempFile)) {
                        FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                    }
                }

                // 构建文件信息
                GenericResponse genericResponse = new GenericResponse(response.headers(), response.bucket(), response.region(), response.object());
                return MinFileObject.builder().object(localTempFile).url(url).response(genericResponse).build();
            }
        } catch (Exception e) {
            log.error("Minio getFile failed.", e);
            throw new ServiceException("Minio getFile failed.", e);
        } finally {
            this.closeClient(client);
        }
    }

    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception {
        MinioClient client = null;
        try {
            client = getClient();

            String name = StorageUtils.generateFilename(parameter);
            String path = StorageUtils.generatePath(parameter);
            String key = StorageUtils.generateKey(parameter, name, path);

            PutObjectArgs args = PutObjectArgs.builder().bucket(getBucketName()).stream(is, parameter.getSize(), -1).contentType(parameter.getContentType()).object(key).build();
            ObjectWriteResponse response = client.putObject(args);
            log.error("Minio putObject response - [{}].", JacksonUtils.toJson(response));

            return getFile(key, false);
        } catch (Exception e) {
            log.error("UploadFile to minio failed.", e);
            throw e;
        } finally {
            this.closeClient(client);
        }
    }

}
