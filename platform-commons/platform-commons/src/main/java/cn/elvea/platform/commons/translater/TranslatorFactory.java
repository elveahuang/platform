package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.translater.baidu.BaiduTranslator;
import cn.elvea.platform.commons.translater.google.GoogleTranslator;

/**
 * TranslatorFactory
 *
 * @author elvea
 * @since 0.0.1
 */
public interface TranslatorFactory {
    /**
     * Translator
     */
    Translator getTranslator();

    /**
     * GoogleTranslator
     */
    GoogleTranslator getGoogleTranslator();

    /**
     * BaiduTranslator
     */
    BaiduTranslator getBaiduTranslator();

    /**
     * AliyunTranslator
     */
    AliyunTranslator getAliyunTranslator();
}
