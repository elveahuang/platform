package cn.elvea.platform.commons.core.autoconfigure.data.core.properties;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomDataSourceProperties.PREFIX)
public class CustomDataSourceProperties {

    public static final String PREFIX = "platform.data.core.ds";

    public static final String MASTER_DATASOURCE_PREFIX = "platform.data.core.ds.master";

    public static final String SLAVE_DATASOURCE_PREFIX = "platform.data.core.ds.slave";

    public static final String JOB_DATASOURCE_PREFIX = "platform.data.core.ds.job";

    private boolean enabled = false;

    private boolean masterSlaveEnabled = false;

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Master master = CustomDataSourceProperties.Master.builder().build();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Slave slave = CustomDataSourceProperties.Slave.builder().build();

    @NestedConfigurationProperty
    private CustomDataSourceProperties.Job job = CustomDataSourceProperties.Job.builder().build();

    @Data
    @Builder
    public static class Master {
        @Builder.Default
        private boolean enabled = true;
    }

    @Data
    @Builder
    public static class Slave {
        @Builder.Default
        private boolean enabled = true;
    }

    @Data
    @Builder
    public static class Job {
        @Builder.Default
        private boolean enabled = true;
    }

}
