package cn.elvea.platform.commons.time.format;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateTimeUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * StandardDateTimeFormatter
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class StandardDateTimeFormatter implements Formatter<TemporalAccessor> {

    private final TimeZoneResolver resolver;

    private final Class<? extends TemporalAccessor> temporalAccessorType;

    @Setter
    @Nullable
    private String custom;

    @Setter
    @Nullable
    private DateTimeFormat.Pattern pattern;

    @Setter
    @NonNull
    private boolean timeZoneConvert = false;

    public StandardDateTimeFormatter(TimeZoneResolver resolver, Class<? extends TemporalAccessor> temporalAccessorType) {
        this.resolver = resolver;
        this.temporalAccessorType = temporalAccessorType;
    }

    @Override
    @NonNull
    public TemporalAccessor parse(@NonNull String text, @NonNull Locale locale) throws ParseException {
        log.debug("StandardDateTimeFormatter parse - text {}", text);
        if (LocalDateTime.class == this.temporalAccessorType) {
            LocalDateTime localDateTime = DateTimeUtils.parse(text, getFormatter(), LocalDateTime.class);
            if (timeZoneConvert) {
                return DateTimeUtils.transfer(localDateTime, resolver.resolveUserZoneId(), resolver.resolveSystemZoneId());
            } else {
                return localDateTime;
            }
        }
        return DateTimeUtils.parse(text, getFormatter(), temporalAccessorType);
    }

    @Override
    @NonNull
    public String print(@NonNull TemporalAccessor date, @NonNull Locale locale) {
        log.debug("StandardDateTimeFormatter print - date {}.", date);
        if (LocalDateTime.class == this.temporalAccessorType && this.timeZoneConvert) {
            LocalDateTime targetLocalDateTime = DateTimeUtils.transfer((LocalDateTime) date, resolver.resolveSystemZoneId(), resolver.resolveUserZoneId());
            return DateTimeUtils.format(targetLocalDateTime, getFormatter());
        }
        return DateTimeUtils.format(date, getFormatter());
    }

    protected DateTimeFormatter getFormatter() {
        DateTimeFormatter formatter = null;
        if (StringUtils.hasLength(this.custom)) {
            formatter = DateTimeFormatter.ofPattern(this.custom);
        } else if (this.pattern != null && this.pattern != DateTimeFormat.Pattern.NONE) {
            formatter = DateTimeUtils.getFormatter(this.pattern);
        }
        return formatter;
    }

}
