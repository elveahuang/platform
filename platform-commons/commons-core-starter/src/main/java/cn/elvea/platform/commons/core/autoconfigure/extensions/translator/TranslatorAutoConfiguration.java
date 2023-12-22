package cn.elvea.platform.commons.core.autoconfigure.extensions.translator;

import cn.elvea.platform.commons.core.autoconfigure.extensions.translator.properties.TranslatorProperties;
import cn.elvea.platform.commons.core.extensions.translator.TranslatorConfig;
import cn.elvea.platform.commons.core.extensions.translator.TranslatorManager;
import lombok.extern.slf4j.Slf4j;
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
@EnableConfigurationProperties({TranslatorProperties.class})
@ConditionalOnProperty(prefix = TranslatorProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class TranslatorAutoConfiguration {

    public TranslatorAutoConfiguration(TranslatorProperties properties) {
        log.debug("Current default translator service is {}", properties.getType());
    }

    /**
     * @return {@link TranslatorManager}
     */
    @Bean
    public TranslatorManager translatorManager(TranslatorProperties properties) {
        TranslatorConfig config = TranslatorConfig.builder()
                .enabled(properties.isEnabled())
                .type(properties.getType())
                .aliyun(properties.getAliyun())
                .baidu(properties.getBaidu()).build();
        return new TranslatorManager(config);
    }

}
