package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Verb
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Verb implements AbstractJsonObject {
    /**
     * IRI
     */
    private URI id;
    /**
     * Language Map
     */
    private LanguageMap display;

    public Verb(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode idNode = jsonNode.path("id");
        if (!idNode.isMissingNode()) {
            this.setId(new URI(idNode.textValue()));
        }

        JsonNode displayNode = jsonNode.path("display");
        if (!displayNode.isMissingNode()) {
            this.setDisplay(new LanguageMap(displayNode));
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        if (this.id != null) {
            node.put("id", this.getId().toString());
        }
        if (this.display != null) {
            node.set("display", this.getDisplay().toJsonNode(version));
        }
        return node;
    }

}
