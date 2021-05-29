package cn.elvea.platform.commons.utils;

/**
 * ObjectUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class ObjectUtils extends org.springframework.util.ObjectUtils {

    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return object != null ? object : defaultValue;
    }

}
