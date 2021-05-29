package cn.elvea.platform.commons.json.jackson;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateTimeUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTimeSerializer
 *
 * @author elvea
 * @since 0.0.1
 */
public final class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements ContextualSerializer {

    private final TimeZoneResolver resolver;

    private String custom;

    private DateTimeFormat.Pattern pattern;

    private boolean timeZoneConvert = false;

    public LocalDateTimeSerializer(TimeZoneResolver resolver) {
        super(LocalDateTime.class);
        this.resolver = resolver;
    }

    public LocalDateTimeSerializer(TimeZoneResolver resolver, DateTimeFormat.Pattern pattern, String custom, boolean timeZoneConvert) {
        this(resolver);
        this.custom = custom;
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (timeZoneConvert) {
            LocalDateTime targetLocalDateTime = DateTimeUtils.transfer(localDateTime, resolver.resolveSystemZoneId(), resolver.resolveUserZoneId());
            generator.writeString(DateTimeUtils.format(targetLocalDateTime, getFormatter()));
        } else {
            generator.writeString(DateTimeUtils.format(localDateTime, DateTimeFormat.Pattern.DATE_TIME));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value format = findFormatOverrides(provider, property, handledType());
        CustomJsonFormat annotation = property.getAnnotation(CustomJsonFormat.class);
        if (annotation != null) {
            return new LocalDateTimeSerializer(resolver, annotation.patten(), annotation.custom(), annotation.timeZoneConvert());
        }
        return new LocalDateTimeSerializer(resolver);
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
