package cn.elvea.platform.commons.utils;

import cn.elvea.platform.base.constants.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static cn.elvea.platform.base.constants.DateTimeConstants.TIME_ZONE_DEFAULT;
import static cn.elvea.platform.base.constants.DateTimeFormat.*;

/**
 * DateUtils
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class DateUtils {

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, TIME_ZONE_DEFAULT);
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, DateTimeFormat.Pattern pattern) {
        return format(date, getPattern(pattern), TIME_ZONE_DEFAULT);
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, DateTimeFormat.Pattern pattern, TimeZone timeZone) {
        return format(date, getPattern(pattern), timeZone);
    }

    /**
     * 格式化日期
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(timeZone);
            return format.format(date);
        }
        return null;
    }

    /**
     * 解析日期
     */
    public static Date parse(String text, String pattern) {
        return parse(text, pattern, TIME_ZONE_DEFAULT);
    }

    /**
     * 解析日期
     */
    public static Date parse(String text, DateTimeFormat.Pattern pattern) {
        return parse(text, getPattern(pattern), TIME_ZONE_DEFAULT);
    }

    /**
     * 解析日期
     */
    public static Date parse(String text, DateTimeFormat.Pattern pattern, TimeZone timeZone) {
        return parse(text, getPattern(pattern), timeZone);
    }

    /**
     * 解析日期
     */
    public static Date parse(String text, String pattern, TimeZone timeZone) {
        if (StringUtils.hasLength(text)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                format.setTimeZone(timeZone);
                return format.parse(text);
            } catch (ParseException e) {
                log.error("DateUtils parse failed. text [{}] - pattern [{}].", text, pattern, e);
            }
        }
        return null;
    }

    /**
     * 获取预设的解析器
     */
    public static String getPattern(Pattern pattern) {
        String text = null;
        if (pattern != null && pattern != Pattern.NONE) {
            switch (pattern) {
                case ISO_TIME:
                    text = ISO_TIME_PATTERN;
                    break;
                case ISO_DATE:
                    text = ISO_DATE_PATTERN;
                    break;
                case ISO_DATE_TIME:
                    text = ISO_DATA_TIME_PATTERN;
                    break;
                case DATE:
                    text = DEFAULT_DATE_PATTERN;
                    break;
                case TIME:
                    text = DEFAULT_TIME_PATTERN;
                    break;
                case SIMPLE_TIME:
                    text = DEFAULT_SIMPLE_TIME_PATTERN;
                    break;
                case DATE_TIME:
                    text = DEFAULT_DATE_TIME_PATTERN;
                    break;
                case SIMPLE_DATE_TIME:
                    text = DEFAULT_SIMPLE_DATE_TIME_PATTERN;
                    break;
                case FULL_DATE_TIME:
                    text = DEFAULT_FULL_DATE_TIME_PATTERN;
                    break;
                default:
                    throw new IllegalStateException("Unsupported pattern: " + pattern);
            }
        }
        return text;
    }

}
