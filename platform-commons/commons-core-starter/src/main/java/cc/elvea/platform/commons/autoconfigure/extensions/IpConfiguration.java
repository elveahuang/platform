package cc.elvea.platform.commons.autoconfigure.extensions;

import cc.elvea.platform.commons.autoconfigure.extensions.properties.IpProperties;
import cc.elvea.platform.commons.extensions.ip.GeoLite;
import cc.elvea.platform.commons.extensions.ip.GlobalIpManager;
import cc.elvea.platform.commons.extensions.ip.LocationEnum;
import cc.elvea.platform.commons.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({IpProperties.class})
@ConditionalOnProperty(prefix = IpProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class IpConfiguration {

    public IpConfiguration(IpProperties properties) {
        log.info("IpConfiguration is enabled.");
        log.info("GeoLite location - {}.", properties.getGeoLite().getLocation());
        log.info("GeoLite path - {}.", properties.getGeoLite().getPath());
    }

    @Bean
    @ConditionalOnMissingBean
    public GeoLite geoLite(IpProperties properties) {
        log.info("Create geoLite...");

        GeoLite geoLite = new GeoLite();
        if (StringUtils.isNotEmpty(properties.getGeoLite().getPath())) {
            Resource resource;
            if (LocationEnum.CLASSPATH.equals(properties.getGeoLite().getLocation())) {
                resource = new ClassPathResource(properties.getGeoLite().getPath());
            } else {
                resource = new FileSystemResource(properties.getGeoLite().getPath());
            }
            geoLite.init(resource);
        }

        GlobalIpManager.setGeoLite(geoLite);
        return geoLite;
    }

}
