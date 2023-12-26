package cn.elvea.platform.commons.core.autoconfigure.core;

import cn.elvea.platform.commons.core.autoconfigure.core.properties.CommonsProperties;
import cn.elvea.platform.commons.core.extensions.time.LegacyDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.core.extensions.time.StandardDateTimeAnnotationFormatterFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({CommonsProperties.class})
@ConditionalOnProperty(prefix = CommonsProperties.PREFIX_WEB, name = "enabled", havingValue = "true")
@AutoConfigureAfter(CommonsProperties.class)
public class WebAutoConfiguration implements WebMvcConfigurer {

    private LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory;

    private StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(legacyDateTimeAnnotationFormatterFactory);
        registry.addFormatterForFieldAnnotation(standardDateTimeAnnotationFormatterFactory);
    }

    @Autowired
    public void setLegacyDateTimeAnnotationFormatterFactory(LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory) {
        this.legacyDateTimeAnnotationFormatterFactory = legacyDateTimeAnnotationFormatterFactory;
    }

    @Autowired
    public void setStandardDateTimeAnnotationFormatterFactory(StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory) {
        this.standardDateTimeAnnotationFormatterFactory = standardDateTimeAnnotationFormatterFactory;
    }

}
