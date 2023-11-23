package cn.elvea.platform.commons.core.autoconfigure.extensions.facebody;

import cn.elvea.platform.commons.core.autoconfigure.extensions.facebody.properties.FaceBodyProperties;
import cn.elvea.platform.commons.core.extensions.facebody.FaceBodyConfig;
import cn.elvea.platform.commons.core.extensions.facebody.FaceBodyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = FaceBodyProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({FaceBodyProperties.class})
public class FaceBodyAutoConfiguration {

    private final FaceBodyProperties properties;

    public FaceBodyAutoConfiguration(FaceBodyProperties properties) {
        log.debug("current default face-body is {}", properties.getType());
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public FaceBodyManager faceBodyManager() {
        FaceBodyConfig config = FaceBodyConfig.builder()
                .enabled(this.properties.getEnabled())
                .type(this.properties.getType())
                .aliyun(this.properties.getAliyun())
                .tencent(this.properties.getTencent())
                .build();
        return new FaceBodyManager(config);
    }

}
