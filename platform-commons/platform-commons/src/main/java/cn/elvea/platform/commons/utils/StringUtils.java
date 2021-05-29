package cn.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

/**
 * StringUtils
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class StringUtils extends org.springframework.util.StringUtils {

    /**
     * 是否不为空
     */
    public static boolean isNotEmpty(@Nullable String val) {
        return !hasText(val);
    }

    public static boolean isBlank(@Nullable final CharSequence cs) {
        return !StringUtils.hasText(cs);
    }

    public static boolean isNotBlank(@Nullable final CharSequence cs) {
        return StringUtils.hasText(cs);
    }

}
