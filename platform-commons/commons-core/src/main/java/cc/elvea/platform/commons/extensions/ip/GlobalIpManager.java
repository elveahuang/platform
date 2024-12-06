package cc.elvea.platform.commons.extensions.ip;

/**
 * @author elvea
 * @since 24.1.0
 */
public class GlobalIpManager {

    private static volatile GeoLite globalGeoLite = new GeoLite();

    public static GeoLite getGeoLite() {
        return globalGeoLite;
    }

    public static void setGeoLite(GeoLite geoLite) {
        globalGeoLite = geoLite;
    }

}
