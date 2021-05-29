package cn.elvea.platform.ds.autoconfigure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CustomDataSourceProperties
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties("platform.datasource")
public class CustomDataSourceConfigProperties {

    /**
     * 是否启用读写分离
     */
    private boolean masterSlaveEnabled = false;

}
