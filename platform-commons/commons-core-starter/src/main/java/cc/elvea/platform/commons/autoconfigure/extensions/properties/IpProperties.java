package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import cc.elvea.platform.commons.extensions.ip.LocationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@ConfigurationProperties(IpProperties.PREFIX)
public class IpProperties {

    public static final String PREFIX = "platform.ip";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private GeoLite geoLite = GeoLite.builder().build();

    @Data
    @Builder
    public static class GeoLite {
        @Builder.Default
        private boolean enabled = false;
        @Builder.Default
        private LocationEnum location = LocationEnum.CLASSPATH;
        @Builder.Default
        private String path = "";
    }

}
