package cn.elvea.platform.base.constants;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static java.time.temporal.ChronoField.MILLI_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

/**
 * DateTimeFormat
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateTimeFormat {

    public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    public final static String DEFAULT_SIMPLE_TIME_PATTERN = "HH:mm";
    public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public final static String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DEFAULT_SIMPLE_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public final static String DEFAULT_FULL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public final static String ISO_DATE_PATTERN = "yyyy-MM-dd";
    public final static String ISO_TIME_PATTERN = "HH:mm:ss.SSSXXX";
    public final static String ISO_DATA_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static final DateTimeFormatter TIME_FORMATTER;
    public static final DateTimeFormatter SIMPLE_TIME_FORMATTER;
    public static final DateTimeFormatter DATE_FORMATTER;
    public static final DateTimeFormatter DATE_TIME_FORMATTER;
    public static final DateTimeFormatter SIMPLE_DATE_TIME_FORMATTER;
    public static final DateTimeFormatter FULL_DATE_TIME_FORMATTER;

    static {
        TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_TIME_PATTERN)
                .toFormatter();

        SIMPLE_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DEFAULT_SIMPLE_TIME_PATTERN)
                .parseDefaulting(SECOND_OF_MINUTE, 1)
                .toFormatter();

        DATE_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_DATE_PATTERN)
                .toFormatter();

        DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_DATE_TIME_PATTERN)
                .parseDefaulting(MILLI_OF_SECOND, 0)
                .toFormatter();

        SIMPLE_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_SIMPLE_DATE_TIME_PATTERN)
                .parseDefaulting(SECOND_OF_MINUTE, 0)
                .parseDefaulting(MILLI_OF_SECOND, 0)
                .toFormatter();

        FULL_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern(DEFAULT_FULL_DATE_TIME_PATTERN)
                .toFormatter();
    }

    public static final Map<Pattern, String> DATE_TIME_PATTERNS;

    static {
        Map<Pattern, String> formats = new EnumMap<>(Pattern.class);
        formats.put(Pattern.DATE, DEFAULT_DATE_PATTERN);
        formats.put(Pattern.DATE_TIME, DEFAULT_DATE_TIME_PATTERN);
        formats.put(Pattern.FULL_DATE_TIME, DEFAULT_FULL_DATE_TIME_PATTERN);
        formats.put(Pattern.SIMPLE_DATE_TIME, DEFAULT_SIMPLE_DATE_TIME_PATTERN);
        formats.put(Pattern.TIME, DEFAULT_TIME_PATTERN);
        formats.put(Pattern.SIMPLE_TIME, DEFAULT_SIMPLE_TIME_PATTERN);
        formats.put(Pattern.ISO_DATE, ISO_DATE_PATTERN);
        formats.put(Pattern.ISO_TIME, ISO_TIME_PATTERN);
        formats.put(Pattern.ISO_DATE_TIME, ISO_DATA_TIME_PATTERN);
        DATE_TIME_PATTERNS = Collections.unmodifiableMap(formats);
    }

    public enum Pattern {
        DATE,
        DATE_TIME,
        FULL_DATE_TIME,
        SIMPLE_DATE_TIME,
        TIME,
        SIMPLE_TIME,
        ISO_DATE,
        ISO_TIME,
        ISO_DATE_TIME,
        NONE,
    }

}
