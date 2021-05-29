package cn.elvea.platform.commons.json.jackson;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * DateDeserializer
 *
 * @author elvea
 * @since 0.0.1
 */
public class DateDeserializer extends StdDeserializer<Date> implements ContextualDeserializer {

    private final TimeZoneResolver resolver;

    private String custom;

    private DateTimeFormat.Pattern pattern;

    private boolean timeZoneConvert = false;

    public DateDeserializer(TimeZoneResolver resolver) {
        super(Date.class);
        this.resolver = resolver;
    }

    public DateDeserializer(TimeZoneResolver resolver, DateTimeFormat.Pattern pattern, String custom, boolean timeZoneConvert) {
        this(resolver);
        this.custom = custom;
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String pattern = DateTimeFormat.DEFAULT_DATE_TIME_PATTERN;
        if (StringUtils.hasLength(this.custom)) {
            pattern = this.custom;
        } else if (this.pattern != null && this.pattern != DateTimeFormat.Pattern.NONE) {
            pattern = DateTimeFormat.DATE_TIME_PATTERNS.get(this.pattern);
        }
        if (timeZoneConvert) {
            return DateUtils.parse(parser.getValueAsString(), pattern, resolver.resolveUserTimeZone());
        } else {
            return DateUtils.parse(parser.getValueAsString(), pattern);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        CustomJsonFormat annotation = property.getAnnotation(CustomJsonFormat.class);
        if (annotation != null) {
            return new DateDeserializer(resolver, annotation.patten(), annotation.custom(), annotation.timeZoneConvert());
        }
        return new DateDeserializer(resolver);
    }

}
