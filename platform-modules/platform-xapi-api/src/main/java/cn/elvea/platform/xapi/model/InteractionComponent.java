package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URISyntaxException;

/**
 * InteractionComponent
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class InteractionComponent implements AbstractJsonObject {
    /**
     *
     */
    private String id;
    /**
     *
     */
    private LanguageMap description;

    public InteractionComponent(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode idNode = jsonNode.path("id");
        if (!idNode.isMissingNode()) {
            this.setId(idNode.textValue());
        }

        JsonNode descriptionNode = jsonNode.path("description");
        if (!descriptionNode.isMissingNode()) {
            this.setDescription(new LanguageMap(descriptionNode));
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.id != null) {
            node.put("id", this.getId());
        }
        if (this.description != null) {
            node.set("description", this.getDescription().toJsonNode(version));
        }

        return node;
    }

}
