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
@ConditionalOnProperty(prefix = FaceBodyProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({FaceBodyProperties.class})
public class FaceBodyAutoConfiguration {

    public FaceBodyAutoConfiguration(FaceBodyProperties properties) {
        log.debug("Current default face-body service is {}", properties.getType());

    }

    @Bean
    @ConditionalOnMissingBean
    public FaceBodyManager faceBodyManager(FaceBodyProperties properties) {
        FaceBodyConfig config = FaceBodyConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .tencent(properties.getTencent()).build();
        return new FaceBodyManager(config);
    }

}
