package cn.elvea.platform.commons.storage.min;

import cn.elvea.platform.commons.storage.StorageService;
import io.minio.MinioClient;

/**
 * MinIOStorageService
 *
 * @author elvea
 * @since 0.0.1
 */
public interface MinStorageService extends StorageService<MinioClient> {
}
