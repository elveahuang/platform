package cc.elvea.platform.commons.extensions.ip;

import lombok.Builder;
import lombok.Data;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
public class GeoLiteConfig {
    @Builder.Default
    private boolean enabled = false;
    @Builder.Default
    private LocationEnum location = LocationEnum.FILE_SYSTEM;
    @Builder.Default
    private String path = "ip/GeoLite2-City.mmdb";
}
