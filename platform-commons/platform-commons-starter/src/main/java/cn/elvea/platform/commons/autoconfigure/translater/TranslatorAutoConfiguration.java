package cn.elvea.platform.commons.autoconfigure.translater;

import cn.elvea.platform.commons.translater.TranslatorFactory;
import cn.elvea.platform.commons.translater.TranslatorFactoryImpl;
import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.translater.baidu.BaiduTranslator;
import cn.elvea.platform.commons.translater.google.GoogleTranslator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TranslatorAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({TranslatorConfigProperties.class})
public class TranslatorAutoConfiguration {

    private final TranslatorConfigProperties config;

    public TranslatorAutoConfiguration(TranslatorConfigProperties config) {
        log.debug("current translator is {}", config.getType());
        this.config = config;
    }

    /**
     * GoogleTranslator
     *
     * @return {@link GoogleTranslator}
     */
    @Bean
    @ConditionalOnMissingBean
    public GoogleTranslator googleTranslator() {
        return new GoogleTranslator(config.getGoogle());
    }

    /**
     * BaiduTranslator
     *
     * @return {@link BaiduTranslator}
     */
    @Bean
    @ConditionalOnMissingBean
    public BaiduTranslator baiduTranslator() {
        return new BaiduTranslator(config.getBaidu());
    }

    /**
     * AliyunTranslator
     *
     * @return {@link AliyunTranslator}
     */
    @Bean
    @ConditionalOnMissingBean
    public AliyunTranslator aliyunTranslator() {
        return new AliyunTranslator(config.getAliyun());
    }

    /**
     * TranslatorFactory
     *
     * @return {@link TranslatorFactory}
     */
    @Bean
    public TranslatorFactory translatorFactory() {
        return new TranslatorFactoryImpl(config);
    }

}
