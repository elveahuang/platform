package cn.elvea.platform.commons.storage;

import cn.elvea.platform.commons.enums.StorageTypeEnum;
import cn.elvea.platform.commons.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.storage.oss.OssStorageConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageConfig implements Serializable {

    @Builder.Default
    private StorageTypeEnum type = StorageTypeEnum.MIN;

    @Builder.Default
    private CosStorageConfig cos = new CosStorageConfig();

    @Builder.Default
    private OssStorageConfig oss = new OssStorageConfig();

    @Builder.Default
    private MinStorageConfig min = new MinStorageConfig();

}
