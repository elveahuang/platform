package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_GROUP;

/**
 * Group
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Group extends Actor {

    private final String objectType = OBJECT_TYPE_GROUP;

    private List<Agent> members = Lists.newArrayList();

    public Group(JsonNode jsonNode) {
        super(jsonNode);

        JsonNode memberNode = jsonNode.path("member");
        if (!memberNode.isMissingNode()) {
            this.members = Lists.newArrayList();
            Iterator<JsonNode> it = memberNode.elements();
            while (it.hasNext()) {
                this.members.add(new Agent(it.next()));
            }
        }
    }

    /**
     * @see AbstractJsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = super.toJsonNode(version);

        node.put("objectType", this.getObjectType());
        if (this.getMembers() != null && this.getMembers().size() > 0) {
            ArrayNode memberNode = JsonMapper.getInstance().createArrayNode();
            for (Agent member : this.getMembers()) {
                memberNode.add(member.toJsonNode(version));
            }
            node.set("member", memberNode);
        }
        return node;
    }

}
