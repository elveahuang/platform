package cn.elvea.platform.commons.core.autoconfigure.extensions.keyword;

import cn.elvea.platform.commons.core.autoconfigure.extensions.keyword.properties.KeywordProperties;
import cn.elvea.platform.commons.core.extensions.keyword.DefaultKeywordManager;
import cn.elvea.platform.commons.core.extensions.keyword.KeywordManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = KeywordProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({KeywordProperties.class})
public class KeywordAutoConfiguration {

    public KeywordAutoConfiguration() {
        log.info("KeywordAutoConfiguration is enabled.");
    }

    /**
     * @return {@link KeywordManager}
     */
    @Bean
    @ConditionalOnMissingBean
    public KeywordManager keywordManager() {
        return new DefaultKeywordManager();
    }

}
