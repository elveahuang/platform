package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Score
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Score implements AbstractJsonObject {
    /**
     *
     */
    private Double scaled;
    /**
     *
     */
    private Double raw;
    /**
     *
     */
    private Double min;
    /**
     *
     */
    private Double max;

    public Score(JsonNode jsonNode) {
        this();

        JsonNode scaledNode = jsonNode.path("scaled");
        if (!scaledNode.isMissingNode()) {
            this.setScaled(scaledNode.doubleValue());
        }

        JsonNode rawNode = jsonNode.path("raw");
        if (!rawNode.isMissingNode()) {
            this.setRaw(rawNode.doubleValue());
        }

        JsonNode minNode = jsonNode.path("min");
        if (!minNode.isMissingNode()) {
            this.setMin(minNode.doubleValue());
        }

        JsonNode maxNode = jsonNode.path("max");
        if (!maxNode.isMissingNode()) {
            this.setMax(maxNode.doubleValue());
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.scaled != null) {
            node.put("scaled", this.getScaled());
        }
        if (this.raw != null) {
            node.put("raw", this.getRaw());
        }
        if (this.min != null) {
            node.put("min", this.getMin());
        }
        if (this.max != null) {
            node.put("max", this.getMax());
        }

        return node;
    }
}
