package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.ip.GeoLiteConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@ConfigurationProperties(IpProperties.PREFIX)
public class IpProperties {

    public static final String PREFIX = "platform.ip";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private GeoLiteConfig geoLite = GeoLiteConfig.builder().build();

}
