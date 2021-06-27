package cn.elvea.platform.xapi.json;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * AbstractJsonObject
 *
 * @author elvea
 */
@Slf4j
public abstract class AbstractJsonObject implements JsonObject, Serializable {

    /**
     * @see JsonObject#toJsonNode()
     */
    @Override
    public ObjectNode toJsonNode() {
        return this.toJsonNode(XApiVersionEnum.latest());
    }

    /**
     * @see JsonObject#toJson(XApiVersionEnum, Boolean)
     */
    @Override
    public String toJson(XApiVersionEnum version, Boolean pretty) {
        ObjectWriter writer = JsonMapper.getWriter(pretty);
        try {
            return writer.writeValueAsString(this.toJsonNode(version));
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
            return "Exception in JSONBase Class: " + e.toString();
        }
    }

    /**
     * @see AbstractJsonObject#toJson(XApiVersionEnum)
     */
    @Override
    public String toJson(XApiVersionEnum version) {
        return this.toJson(version, false);
    }

    /**
     * @see JsonObject#toJson(Boolean)
     */
    @Override
    public String toJson(Boolean pretty) {
        return this.toJson(XApiVersionEnum.latest(), pretty);
    }

    /**
     * @see JsonObject#toJson()
     */
    @Override
    public String toJson() {
        return this.toJson(XApiVersionEnum.latest(), false);
    }

}
