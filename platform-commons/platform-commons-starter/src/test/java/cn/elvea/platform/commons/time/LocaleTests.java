package cn.elvea.platform.commons.time;

import org.junit.jupiter.api.Test;

import java.util.Locale;

/**
 * 谷歌翻译单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class LocaleTests {

    @Test
    public void test() {
        System.out.println("印尼语");
        printLocale(new Locale("id"));
        System.out.println("简体中文");
        printLocale(Locale.SIMPLIFIED_CHINESE);
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale l : locales) {
            printLocale(l);
        }
    }

    private void printLocale(Locale locale) {
        System.out.printf("toString - %s \r\n", locale.toString());
        System.out.printf("toLanguageTag - %s \r\n", locale.toLanguageTag());
        System.out.printf("getCountry - %s \r\n", locale.getCountry());
        System.out.printf("getDisplayCountry - %s \r\n", locale.getDisplayCountry());
        System.out.printf("getLanguage - %s \r\n", locale.getLanguage());
        System.out.printf("getDisplayLanguage - %s \r\n", locale.getDisplayLanguage());
        System.out.printf("getDisplayName - %s \r\n", locale.getDisplayName());
        System.out.printf("getVariant - %s \r\n", locale.getVariant());
    }

}
