package cn.elvea.platform.xapi.json;

import cn.elvea.platform.xapi.json.AbstractJsonObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * JsonModule
 *
 * @author elvea
 */
public class JsonModule {

    public static class Serializer extends JsonSerializer<AbstractJsonObject> {
        @Override
        public void serialize(AbstractJsonObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        }
    }

}
