package cn.elvea.platform.commons.time.format;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * LegacyDateTimeFormatter
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class LegacyDateTimeFormatter implements Formatter<Date> {

    @Setter
    private TimeZoneResolver resolver;

    @Setter
    @Nullable
    private Object source;

    @Setter
    @Nullable
    private String custom;

    @Setter
    @Nullable
    private DateTimeFormat.Pattern pattern;

    @Setter
    @NonNull
    private boolean timeZoneConvert = false;

    @Override
    public Date parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        log.debug("LegacyDateTimeFormatter.parse - text {}", text);
        String pattern = DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.hasLength(this.custom)) {
            pattern = this.custom;
        } else if (this.pattern != null && this.pattern != DateTimeFormat.Pattern.NONE) {
            pattern = DateTimeFormat.DATE_TIME_PATTERNS.get(this.pattern);
        }
        if (timeZoneConvert) {
            return DateUtils.parse(text, pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.parse(text, pattern);
        }
    }

    @Override
    public String print(@NonNull Date date, @NonNull Locale locale) {
        log.debug("LegacyDateTimeFormatter.print - date {}.", date);
        String pattern = DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.hasLength(this.custom)) {
            pattern = this.custom;
        } else if (this.pattern != null && this.pattern != DateTimeFormat.Pattern.NONE) {
            pattern = DateTimeFormat.DATE_TIME_PATTERNS.get(this.pattern);
        }
        if (timeZoneConvert) {
            return DateUtils.format(date, pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.format(date, pattern);
        }
    }

}
