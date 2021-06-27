package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_STATEMENT_REF;

/**
 * Statement References
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatementRef implements AbstractObject {
    /**
     * objectType
     */
    private final String objectType = OBJECT_TYPE_STATEMENT_REF;
    /**
     * ID
     */
    private UUID id;

    public StatementRef(JsonNode jsonNode) {
        JsonNode idNode = jsonNode.path("id");
        if (!idNode.isMissingNode()) {
            this.setId(UUID.fromString(idNode.textValue()));
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        node.put("objectType", this.objectType);
        node.put("id", this.getId().toString());
        return node;
    }

}
