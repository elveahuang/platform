package cn.elvea.platform.xapi.json;

import cn.elvea.platform.xapi.exception.XAPIException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * JsonMapper
 *
 * @author elvea
 */
@Slf4j
public class JsonMapper {

    private static final ObjectMapper instance = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return instance;
    }

    public static ObjectWriter getWriter(Boolean pretty) {
        ObjectMapper mapper = getInstance();

        ObjectWriter writer;
        if (pretty) {
            writer = mapper.writer().withDefaultPrettyPrinter();
        } else {
            writer = mapper.writer();
        }

        return writer;
    }

    public static JsonNode toJsonNode(String json) throws XAPIException {
        try {
            return JsonMapper.getInstance().readValue(json, JsonNode.class);
        } catch (IOException e) {
            log.error("failed to covert json to json node.", e);
            throw new XAPIException("failed to covert json to json node.");
        }
    }

}
