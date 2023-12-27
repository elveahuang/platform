package cn.elvea.platform.commons.core.autoconfigure.web.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WebProperties.PREFIX)
public class WebProperties implements Serializable {

    public static final String PREFIX = "platform.web";

    private boolean enabled = false;

}
