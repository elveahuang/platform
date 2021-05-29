package cn.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Objects;

import static cn.elvea.platform.base.constants.DateTimeConstants.ZONE_ID_DEFAULT;
import static cn.elvea.platform.base.constants.DateTimeFormat.*;

/**
 * DateTimeUtils
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class DateTimeUtils {

    /**
     * 转换时区
     */
    public static LocalDateTime transfer(LocalDateTime localDateTime, ZoneId targetZoneId) {
        return transfer(localDateTime, ZONE_ID_DEFAULT, targetZoneId);
    }

    /**
     * 转换时区
     */
    public static LocalDateTime transfer(LocalDateTime localDateTime, ZoneId fromZoneId, ZoneId targetZoneId) {
        return localDateTime.atZone(fromZoneId).withZoneSameInstant(targetZoneId).toLocalDateTime();
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, String patten) {
        return format(e, createFormatter(patten));
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, Pattern pattern) {
        return format(e, getFormatter(pattern));
    }

    /**
     * 格式化日期
     */
    public static <E extends TemporalAccessor> String format(E e, DateTimeFormatter formatter) {
        return formatter.format(e);
    }

    /**
     * 解析日期
     */
    public static <T extends TemporalAccessor> T parse(String text, String pattern, Class<T> classType) {
        return parse(text, createFormatter(pattern), classType);
    }

    /**
     * 解析日期
     */
    public static <T extends TemporalAccessor> T parse(String text, Pattern pattern, Class<T> classType) {
        return parse(text, getFormatter(pattern), classType);
    }

    /**
     * 解析日期
     */
    @SuppressWarnings("unchecked")
    public static <T extends TemporalAccessor> T parse(String text, DateTimeFormatter formatter, Class<T> classType) {
        if (LocalDate.class == classType) {
            return (T) LocalDate.parse(text, formatter);
        } else if (LocalTime.class == classType) {
            return (T) LocalTime.parse(text, formatter);
        } else if (LocalDateTime.class == classType) {
            return (T) LocalDateTime.parse(text, formatter);
        } else if (ZonedDateTime.class == classType) {
            return (T) ZonedDateTime.parse(text, formatter);
        } else if (OffsetDateTime.class == classType) {
            return (T) OffsetDateTime.parse(text, formatter);
        } else if (OffsetTime.class == classType) {
            return (T) OffsetTime.parse(text, formatter);
        } else {
            log.error("DateTimeUtils parse failed. text [{}].", text);
            throw new IllegalStateException("Unsupported TemporalAccessor type: " + classType);
        }
    }

    /**
     * 获取预设的解析器
     */
    public static DateTimeFormatter getFormatter(Pattern pattern) {
        DateTimeFormatter formatter = null;
        if (pattern != null && pattern != Pattern.NONE) {
            switch (pattern) {
                case ISO_TIME:
                    formatter = DateTimeFormatter.ISO_TIME;
                    break;
                case ISO_DATE:
                    formatter = DateTimeFormatter.ISO_DATE;
                    break;
                case ISO_DATE_TIME:
                    formatter = DateTimeFormatter.ISO_DATE_TIME;
                    break;
                case DATE:
                    formatter = DATE_FORMATTER;
                    break;
                case TIME:
                    formatter = TIME_FORMATTER;
                    break;
                case SIMPLE_TIME:
                    formatter = SIMPLE_TIME_FORMATTER;
                    break;
                case DATE_TIME:
                    formatter = DATE_TIME_FORMATTER;
                    break;
                case SIMPLE_DATE_TIME:
                    formatter = SIMPLE_DATE_TIME_FORMATTER;
                    break;
                case FULL_DATE_TIME:
                    formatter = FULL_DATE_TIME_FORMATTER;
                    break;
                default:
                    throw new IllegalStateException("Unsupported pattern: " + pattern);
            }
        }
        return formatter;
    }

    public static DateTimeFormatter createFormatter(String pattern) {
        Objects.requireNonNull(pattern, "pattern");
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "date");
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID_DEFAULT);
    }

    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    public static LocalTime toLocalTime(Date date) {
        return toLocalDateTime(date).toLocalTime();
    }

}
