package cn.elvea.platform.commons.core.autoconfigure.web;

import cn.elvea.platform.commons.core.autoconfigure.web.properties.WebProperties;
import cn.elvea.platform.commons.core.web.i18n.CustomLocaleResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({WebMvcAutoConfiguration.class})
@EnableConfigurationProperties({WebProperties.class})
@ConditionalOnProperty(prefix = WebProperties.PREFIX, name = "enabled", havingValue = "true")
public class WebCoreAutoConfiguration {

    public WebCoreAutoConfiguration() {
        log.info("WebCoreAutoConfiguration is enabled.");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }

}
