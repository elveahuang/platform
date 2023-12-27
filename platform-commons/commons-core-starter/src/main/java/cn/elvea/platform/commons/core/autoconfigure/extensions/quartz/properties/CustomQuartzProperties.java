package cn.elvea.platform.commons.core.autoconfigure.extensions.quartz.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@ConfigurationProperties(CustomQuartzProperties.PREFIX)
public class CustomQuartzProperties {

    public static final String PREFIX = "platform.quartz";

    /**
     * 是否开启定时任务
     */
    private boolean enabled = true;

}
