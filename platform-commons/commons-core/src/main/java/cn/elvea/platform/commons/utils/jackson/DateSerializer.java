package cn.elvea.platform.commons.utils.jackson;

import cn.elvea.platform.commons.annotations.JsonFormat;
import cn.elvea.platform.commons.constants.DateTimeConstants;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.commons.utils.ObjectUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.commons.utils.time.TimeZoneManager;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DateSerializer extends StdSerializer<Date> implements ContextualSerializer {

    private String pattern;

    private boolean timeZoneConvert = false;

    public DateSerializer() {
        super(Date.class);
    }

    public DateSerializer(String pattern, boolean timeZoneConvert) {
        super(Date.class);
        this.pattern = pattern;
        this.timeZoneConvert = timeZoneConvert;
    }

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) throws IOException {
        String pattern = StringUtils.isNotEmpty(this.pattern) ? this.pattern : DateTimeConstants.DEFAULT_DATE_TIME_PATTERN;
        if (timeZoneConvert) {
            generator.writeString(DateUtils.format(date, pattern, TimeZoneManager.getResolver().resolveUserTimeZone()));
        } else {
            generator.writeString(DateUtils.format(date, pattern));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) {
        if (!ObjectUtils.isEmpty(property)) {
            com.fasterxml.jackson.annotation.JsonFormat.Value format = findFormatOverrides(provider, property, handledType());
            JsonFormat annotation = property.getAnnotation(JsonFormat.class);
            if (annotation != null) {
                return new DateSerializer(annotation.pattern(), annotation.timeZoneConvert());
            }
        }
        return new DateSerializer();
    }

}
