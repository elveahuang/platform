package cn.elvea.platform.commons.translater;

/**
 * 翻译器
 *
 * @author elvea
 * @since 0.0.1
 */
public interface Translator {

    /**
     * 翻译
     *
     * @param sourceLang 源语言
     * @param targetLang 目标语言
     * @param text       文本
     * @return 翻译文本
     */
    String translate(String sourceLang, String targetLang, String text);

}
