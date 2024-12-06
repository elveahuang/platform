package cc.elvea.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@ConfigurationProperties(QuartzCustomProperties.PREFIX)
public class QuartzCustomProperties {

    public static final String PREFIX = "platform.quartz";

    /**
     * 是否开启定时任务
     */
    private boolean enabled = true;

}
