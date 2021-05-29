package cn.elvea.platform.commons.autoconfigure.time;

import cn.elvea.platform.commons.autoconfigure.CommonsAutoConfiguration;
import cn.elvea.platform.commons.time.format.LegacyDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.time.format.StandardDateTimeAnnotationFormatterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * DateTimeWebAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@AutoConfigureAfter(CommonsAutoConfiguration.class)
public class DateTimeAutoConfiguration implements WebMvcConfigurer {

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
