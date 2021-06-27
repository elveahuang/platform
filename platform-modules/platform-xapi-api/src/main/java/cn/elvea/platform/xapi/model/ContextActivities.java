package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.URISyntaxException;
import java.util.List;

/**
 * ContextActivities
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ContextActivities implements AbstractJsonObject {
    /**
     *
     */
    private List<Activity> parent;
    /**
     *
     */
    private List<Activity> grouping;
    /**
     *
     */
    private List<Activity> other;
    /**
     *
     */
    private List<Activity> category;

    public ContextActivities(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode parentNode = jsonNode.path("parent");
        if (!parentNode.isMissingNode()) {
            this.parent = Lists.newArrayList();

            if (parentNode.isArray()) {
                for (JsonNode element : parentNode) {
                    this.parent.add(new Activity(element));
                }
            } else {
                this.parent.add(new Activity(parentNode));
            }
        }

        JsonNode groupingNode = jsonNode.path("grouping");
        if (!groupingNode.isMissingNode()) {
            this.grouping = Lists.newArrayList();

            if (groupingNode.isArray()) {
                for (JsonNode element : groupingNode) {
                    this.grouping.add(new Activity(element));
                }
            } else {
                this.grouping.add(new Activity(groupingNode));
            }
        }

        JsonNode otherNode = jsonNode.path("other");
        if (!otherNode.isMissingNode()) {
            this.other = Lists.newArrayList();

            if (otherNode.isArray()) {
                for (JsonNode element : otherNode) {
                    this.other.add(new Activity(element));
                }
            } else {
                this.other.add(new Activity(otherNode));
            }
        }

        JsonNode categoryNode = jsonNode.path("category");
        if (!categoryNode.isMissingNode()) {
            this.category = Lists.newArrayList();

            if (categoryNode.isArray()) {
                for (JsonNode element : categoryNode) {
                    this.category.add(new Activity(element));
                }
            } else {
                this.category.add(new Activity(categoryNode));
            }
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.parent != null && this.parent.size() > 0) {
            ArrayNode parent = JsonMapper.getInstance().createArrayNode();
            for (Activity element : this.getParent()) {
                parent.add(element.toJsonNode(version));
            }
            node.set("parent", parent);
        }
        if (this.grouping != null && this.grouping.size() > 0) {
            ArrayNode grouping = JsonMapper.getInstance().createArrayNode();
            for (Activity element : this.getGrouping()) {
                grouping.add(element.toJsonNode(version));
            }
            node.set("grouping", grouping);
        }
        if (this.other != null && this.other.size() > 0) {
            ArrayNode other = JsonMapper.getInstance().createArrayNode();
            for (Activity element : this.getOther()) {
                other.add(element.toJsonNode(version));
            }
            node.set("other", other);
        }
        if (this.category != null && this.category.size() > 0) {
            ArrayNode category = JsonMapper.getInstance().createArrayNode();
            for (Activity element : this.getCategory()) {
                category.add(element.toJsonNode(version));
            }
            node.set("category", category);
        }

        return node;
    }

}
