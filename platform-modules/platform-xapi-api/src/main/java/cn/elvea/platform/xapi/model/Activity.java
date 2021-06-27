package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URISyntaxException;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_ACTIVITY;

/**
 * Activity
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity implements AbstractObject {
    /**
     *
     */
    private final String objectType = OBJECT_TYPE_ACTIVITY;
    /**
     *
     */
    private String id;
    /**
     *
     */
    private ActivityDefinition definition;

    public Activity(String id) {
        this.id = id;
    }

    public Activity(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode idNode = jsonNode.path("id");
        if (!idNode.isMissingNode()) {
            this.setId(idNode.textValue());
        }

        JsonNode definitionNode = jsonNode.path("definition");
        if (!definitionNode.isMissingNode()) {
            this.setDefinition(new ActivityDefinition(definitionNode));
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        node.put("objectType", this.getObjectType());
        if (this.id != null) {
            node.put("id", this.getId().toString());
        }
        if (this.definition != null) {
            node.set("definition", this.getDefinition().toJsonNode());
        }
        return node;
    }

}
