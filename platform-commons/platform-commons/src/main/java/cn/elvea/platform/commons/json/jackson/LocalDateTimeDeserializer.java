package cn.elvea.platform.commons.json.jackson;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.time.TimeZoneResolver;
import cn.elvea.platform.commons.utils.DateTimeUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTimeDeserializer
 *
 * @author elvea
 * @since 0.0.1
 */
public final class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> implements ContextualDeserializer {

    private final TimeZoneResolver resolver;

    private String custom;

    private DateTimeFormat.Pattern pattern;

    private boolean timeZoneConvert = false;

    public LocalDateTimeDeserializer(TimeZoneResolver resolver) {
        super(LocalDateTime.class);
        this.resolver = resolver;
    }

    public LocalDateTimeDeserializer(TimeZoneResolver resolver, DateTimeFormat.Pattern pattern, String custom, boolean timeZoneConvert) {
        this(resolver);
        this.custom = custom;
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        LocalDateTime localDateTime = DateTimeUtils.parse(parser.getText(), getFormatter(), LocalDateTime.class);
        if (timeZoneConvert) {
            return DateTimeUtils.transfer(localDateTime, resolver.resolveUserZoneId(), resolver.resolveSystemZoneId());
        } else {
            return localDateTime;
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) throws JsonMappingException {
        JsonFormat.Value format = findFormatOverrides(context, property, handledType());
        CustomJsonFormat annotation = property.getAnnotation(CustomJsonFormat.class);
        if (annotation != null) {
            return new LocalDateTimeDeserializer(resolver, annotation.patten(), annotation.custom(), annotation.timeZoneConvert());
        }
        return new LocalDateTimeDeserializer(resolver);
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
