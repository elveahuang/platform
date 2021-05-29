package cn.elvea.platform.commons.autoconfigure;

import cn.elvea.platform.commons.Context;
import cn.elvea.platform.commons.json.jackson.CustomJsonModule;
import cn.elvea.platform.commons.language.DefaultLanguageResolver;
import cn.elvea.platform.commons.language.LanguageResolver;
import cn.elvea.platform.commons.time.DefaultTimeZoneResolver;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.time.format.LegacyDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.time.format.StandardDateTimeAnnotationFormatterFactory;
import cn.elvea.platform.commons.utils.SpringUtils;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * CommonsAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({CommonsConfigProperties.class})
public class CommonsAutoConfiguration {

    public CommonsAutoConfiguration(CommonsConfigProperties config) {
        log.debug("Application run in {} mode.", config.isDebug() ? "development" : "production");
    }

    /**
     * Context
     *
     * @return {@link Context}
     */
    @Bean
    @ConditionalOnMissingBean
    public Context context(CommonsConfigProperties config) {
        return new Context(config);
    }

    /**
     * SpringUtils
     *
     * @return {@link SpringUtils}
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringUtils springUtils() {
        return new SpringUtils();
    }

    /**
     * TimeZoneResolver
     *
     * @return {@link TimeZoneResolver}
     */
    @Bean
    @ConditionalOnMissingBean
    public TimeZoneResolver timeZoneResolver(CommonsConfigProperties config) {
        return new DefaultTimeZoneResolver(config.getUserZoneId(), config.getSystemZoneId());
    }

    /**
     * LegacyDateTimeAnnotationFormatterFactory
     *
     * @return {@link LegacyDateTimeAnnotationFormatterFactory}
     */
    @Bean
    @ConditionalOnMissingBean
    public LegacyDateTimeAnnotationFormatterFactory legacyDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new LegacyDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }

    /**
     * StandardDateTimeAnnotationFormatterFactory
     *
     * @return {@link StandardDateTimeAnnotationFormatterFactory}
     */
    @Bean
    @ConditionalOnMissingBean
    public StandardDateTimeAnnotationFormatterFactory standardDateTimeAnnotationFormatterFactory(TimeZoneResolver timeZoneResolver) {
        return new StandardDateTimeAnnotationFormatterFactory(timeZoneResolver);
    }


    /**
     * LanguageResolver
     *
     * @return {@link LanguageResolver}
     */
    @Bean
    @ConditionalOnMissingBean
    public LanguageResolver languageResolver(CommonsConfigProperties config) {
        return new DefaultLanguageResolver(config.getLanguage());
    }

    /**
     * Jackson Customizer
     */
    @Bean
    public CustomJsonModule customJsonModule(TimeZoneResolver resolver) {
        return new CustomJsonModule(resolver);
    }

    /**
     * Jackson Customizer
     */
    @Bean
    public CustomObjectMapperBuilderCustomizer objectMapperBuilderCustomizer(CustomJsonModule customJsonModule) {
        return new CustomObjectMapperBuilderCustomizer(customJsonModule);
    }

    /**
     * CustomObjectMapperBuilderCustomizer
     */
    static class CustomObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {

        public final CustomJsonModule customJsonModule;

        CustomObjectMapperBuilderCustomizer(CustomJsonModule customJsonModule) {
            this.customJsonModule = customJsonModule;
        }

        @Override
        public void customize(Jackson2ObjectMapperBuilder builder) {
            builder.modulesToInstall(new JavaTimeModule());
            builder.modulesToInstall(this.customJsonModule);
        }

        @Override
        public int getOrder() {
            return 1;
        }

    }

}
