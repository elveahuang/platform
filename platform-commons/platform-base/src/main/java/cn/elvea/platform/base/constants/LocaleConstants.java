package cn.elvea.platform.base.constants;

import java.util.Locale;

/**
 * 国际化相关全局静态变量
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class LocaleConstants {
    /**
     * 默认语言
     */
    public final static Locale DEFAULT_LOCALE = LocaleConstants.SIMPLIFIED_CHINESE;
    public final static String DEFAULT_LANGUAGE = "zh_CN";

    public final static Locale SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE;
    public final static Locale TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE;
    public final static Locale ENGLISH = Locale.US;
}
