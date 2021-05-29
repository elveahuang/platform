package cn.elvea.platform.commons.json.jackson;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateUtils;
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
import java.util.Date;

/**
 * DateSerializer
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateSerializer extends StdSerializer<Date> implements ContextualSerializer {

    private final TimeZoneResolver resolver;

    private String custom;

    private DateTimeFormat.Pattern pattern;

    private boolean timeZoneConvert = false;

    public DateSerializer(TimeZoneResolver resolver) {
        super(Date.class);
        this.resolver = resolver;
    }

    public DateSerializer(TimeZoneResolver resolver, DateTimeFormat.Pattern pattern, String custom, boolean timeZoneConvert) {
        this(resolver);
        this.custom = custom;
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        String pattern = DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.hasLength(this.custom)) {
            pattern = this.custom;
        } else if (this.pattern != null && this.pattern != DateTimeFormat.Pattern.NONE) {
            pattern = DateTimeFormat.DATE_TIME_PATTERNS.get(this.pattern);
        }
        if (timeZoneConvert) {
            generator.writeString(DateUtils.format(date, pattern, resolver.resolveUserTimeZone()));
        } else {
            generator.writeString(DateUtils.format(date, pattern));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value format = findFormatOverrides(provider, property, handledType());
        CustomJsonFormat annotation = property.getAnnotation(CustomJsonFormat.class);
        if (annotation != null) {
            return new DateSerializer(resolver, annotation.patten(), annotation.custom(), annotation.timeZoneConvert());
        }
        return new DateSerializer(resolver);
    }

}
