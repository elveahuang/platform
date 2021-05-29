package cn.elvea.platform.commons.storage;

import cn.elvea.platform.commons.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.storage.oss.OssStorageConfig;
import cn.elvea.platform.commons.storage.enums.StorageType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * StorageConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class StorageConfig implements Serializable {
    private StorageType type = StorageType.MIN;
    private CosStorageConfig cos = new CosStorageConfig();
    private OssStorageConfig oss = new OssStorageConfig();
    private MinStorageConfig min = new MinStorageConfig();
}
