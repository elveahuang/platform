package cn.elvea.platform.commons.language;

import cn.elvea.platform.base.constants.LocaleConstants;

/**
 * 默认语言解析器，直接返回当前系统的默认时区。
 *
 * @author elvea
 * @since 0.0.1
 */
public class DefaultLanguageResolver implements LanguageResolver {

    private String language;

    public DefaultLanguageResolver() {
        this(LocaleConstants.DEFAULT_LANGUAGE);
    }

    public DefaultLanguageResolver(String language) {
        this.language = language;
    }

    @Override
    public String resolveLanguage() {
        return this.language;
    }

}
