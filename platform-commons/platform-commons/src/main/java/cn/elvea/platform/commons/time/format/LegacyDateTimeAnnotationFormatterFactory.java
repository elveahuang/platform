package cn.elvea.platform.commons.time.format;

import cn.elvea.platform.commons.time.TimeZoneResolver;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 传统的日期时间处理器
 *
 * @author elvea
 * @since 0.0.1
 */
public class LegacyDateTimeAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
        implements AnnotationFormatterFactory<CustomDateTimeFormat> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = Set.of(Date.class, Calendar.class);
    }

    private final TimeZoneResolver resolver;

    public LegacyDateTimeAnnotationFormatterFactory(TimeZoneResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    @NonNull
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    @NonNull
    public Printer<?> getPrinter(@NonNull CustomDateTimeFormat annotation, @NonNull Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    @Override
    @NonNull
    public Parser<?> getParser(@NonNull CustomDateTimeFormat annotation, @NonNull Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    protected Formatter<Date> getFormatter(CustomDateTimeFormat annotation, Class<?> fieldType) {
        LegacyDateTimeFormatter formatter = new LegacyDateTimeFormatter();
        formatter.setResolver(resolver);
        formatter.setSource(annotation);
        formatter.setPattern(annotation.patten());
        formatter.setTimeZoneConvert(annotation.timeZoneConvert());
        String custom = resolveEmbeddedValue(annotation.custom());
        if (StringUtils.hasLength(custom)) {
            formatter.setCustom(custom);
        }
        return formatter;
    }

}
