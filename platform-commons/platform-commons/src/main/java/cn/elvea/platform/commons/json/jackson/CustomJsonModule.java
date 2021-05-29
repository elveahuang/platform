package cn.elvea.platform.commons.json.jackson;

import cn.elvea.platform.commons.time.TimeZoneResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * CustomJsonModule
 *
 * @author elvea
 * @since 0.0.1
 */
public class CustomJsonModule extends SimpleModule {

    private final TimeZoneResolver resolver;

    public CustomJsonModule(TimeZoneResolver resolver) {
        this.resolver = resolver;

        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addDeserializer(Date.class, new DateDeserializer(this.resolver));
        this.addSerializer(Date.class, new DateSerializer(this.resolver));
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(this.resolver));
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(this.resolver));
    }

}
