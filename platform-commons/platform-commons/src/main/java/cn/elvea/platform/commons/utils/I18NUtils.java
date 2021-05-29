package cn.elvea.platform.commons.utils;

import java.util.Arrays;
import java.util.Locale;

/**
 * I18NUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class I18NUtils {

    public static Locale[] getAvailableLocales() {
        return Locale.getAvailableLocales();
    }

    public static void main(String[] args) {
        Arrays.stream(I18NUtils.getAvailableLocales()).iterator().forEachRemaining(System.out::println);
    }

}
