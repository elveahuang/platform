package cn.elvea.platform.commons.autoconfigure.storage.properties;

import cn.elvea.platform.commons.enums.StorageTypeEnum;
import cn.elvea.platform.commons.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.storage.oss.OssStorageConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = StorageProperties.PREFIX)
public class StorageProperties {

    public static final String PREFIX = "platform.storage";

    private boolean enabled = false;

    private StorageTypeEnum type = StorageTypeEnum.MIN;

    @NestedConfigurationProperty
    private CosStorageConfig cos = new CosStorageConfig();

    @NestedConfigurationProperty
    private OssStorageConfig oss = new OssStorageConfig();

    @NestedConfigurationProperty
    private MinStorageConfig min = new MinStorageConfig();

}
