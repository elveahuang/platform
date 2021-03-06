package cn.elvea.platform.commons.time.format;

import cn.elvea.platform.base.constants.DateTimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomDateTimeFormat
 *
 * @author elvea
 * @since 0.0.1
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDateTimeFormat {

    /**
     * 内置日期格式。
     */
    DateTimeFormat.Pattern patten() default DateTimeFormat.Pattern.NONE;

    /**
     * 自定义日期格式，优先级高于内置日期格式。
     */
    String custom() default "";

    /**
     * 是否在转换时区，把用户提交的时间，按用户时区换算为系统时区的日期时间。
     */
    boolean timeZoneConvert() default false;

}
