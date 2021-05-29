package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.translater.baidu.BaiduTranslator;
import cn.elvea.platform.commons.translater.google.GoogleTranslator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * TranslatorFactory
 *
 * @author elvea
 * @see TranslatorFactory
 * @since 0.0.1
 */
public class TranslatorFactoryImpl implements TranslatorFactory, ApplicationContextAware {

    private ApplicationContext context;

    private final TranslatorConfig config;

    public TranslatorFactoryImpl(TranslatorConfig config) {
        this.config = config;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext context) throws BeansException {
        this.context = context;
    }

    /**
     * @see TranslatorFactory#getTranslator()
     */
    public Translator getTranslator() {
        if (TranslatorType.BAIDU.equals(this.config.getType())) {
            return this.getBaiduTranslator();
        } else if (TranslatorType.ALIYUN.equals(this.config.getType())) {
            return this.getAliyunTranslator();
        } else if (TranslatorType.GOOGLE.equals(this.config.getType())) {
            return this.getGoogleTranslator();
        }
        return this.getGoogleTranslator();
    }

    /**
     * @see TranslatorFactory#getGoogleTranslator()
     */
    public GoogleTranslator getGoogleTranslator() {
        return this.context.getBean(GoogleTranslator.class);
    }

    /**
     * @see TranslatorFactory#getBaiduTranslator()
     */
    public BaiduTranslator getBaiduTranslator() {
        return this.context.getBean(BaiduTranslator.class);
    }

    /**
     * @see TranslatorFactory#getAliyunTranslator()
     */
    public AliyunTranslator getAliyunTranslator() {
        return this.context.getBean(AliyunTranslator.class);
    }

}
