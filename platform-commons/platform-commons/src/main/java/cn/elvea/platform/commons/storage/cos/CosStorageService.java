package cn.elvea.platform.commons.storage.cos;

import cn.elvea.platform.commons.storage.StorageService;
import com.qcloud.cos.COSClient;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @since 0.0.1
 */
public interface CosStorageService extends StorageService<COSClient> {
}
