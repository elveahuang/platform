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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static cn.elvea.platform.xapi.model.InteractionType.getByString;

/**
 * ActivityDefinition
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ActivityDefinition implements AbstractJsonObject {
    /**
     *
     */
    private LanguageMap name;
    /**
     *
     */
    private LanguageMap description;
    /**
     *
     */
    private URI type;
    /**
     *
     */
    private URI moreInfo;
    /**
     *
     */
    private Extensions extensions;
    /**
     *
     */
    private InteractionType interactionType;
    /**
     *
     */
    private List<String> correctResponsesPattern;
    /**
     *
     */
    private List<InteractionComponent> choices;
    /**
     *
     */
    private List<InteractionComponent> scale;
    /**
     *
     */
    private List<InteractionComponent> source;
    /**
     *
     */
    private List<InteractionComponent> target;
    /**
     *
     */
    private List<InteractionComponent> steps;

    public ActivityDefinition(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode typeNode = jsonNode.path("type");
        if (!typeNode.isMissingNode()) {
            this.setType(new URI(typeNode.textValue()));
        }

        JsonNode moreInfoNode = jsonNode.path("moreInfo");
        if (!moreInfoNode.isMissingNode()) {
            this.setMoreInfo(new URI(moreInfoNode.textValue()));
        }

        JsonNode nameNode = jsonNode.path("name");
        if (!nameNode.isMissingNode()) {
            this.setName(new LanguageMap(nameNode));
        }

        JsonNode descNode = jsonNode.path("description");
        if (!descNode.isMissingNode()) {
            this.setDescription(new LanguageMap(descNode));
        }

        JsonNode extensionsNode = jsonNode.path("extensions");
        if (!extensionsNode.isMissingNode()) {
            this.setExtensions(new Extensions(extensionsNode));
        }

        JsonNode interactionTypeNode = jsonNode.path("interactionType");
        if (!interactionTypeNode.isMissingNode()) {
            InteractionType intType = getByString(interactionTypeNode.textValue());
            this.setInteractionType(intType);
        }

        JsonNode correctResponsesPatternNode = jsonNode.path("correctResponsesPattern");
        if (!correctResponsesPatternNode.isMissingNode()) {
            this.correctResponsesPattern = Lists.newArrayList();
            for (JsonNode element : correctResponsesPatternNode) {
                this.correctResponsesPattern.add(element.textValue());
            }
        }

        JsonNode choicesNode = jsonNode.path("choices");
        if (!choicesNode.isMissingNode()) {
            this.choices = Lists.newArrayList();
            for (JsonNode element : choicesNode) {
                this.choices.add(new InteractionComponent(element));
            }
        }

        JsonNode scaleNode = jsonNode.path("scale");
        if (!scaleNode.isMissingNode()) {
            this.scale = Lists.newArrayList();
            for (JsonNode element : scaleNode) {
                this.scale.add(new InteractionComponent(element));
            }
        }

        JsonNode sourceNode = jsonNode.path("source");
        if (!sourceNode.isMissingNode()) {
            this.source = Lists.newArrayList();
            for (JsonNode element : sourceNode) {
                this.source.add(new InteractionComponent(element));
            }
        }

        JsonNode targetNode = jsonNode.path("target");
        if (!targetNode.isMissingNode()) {
            this.target = Lists.newArrayList();
            for (JsonNode element : targetNode) {
                this.target.add(new InteractionComponent(element));
            }
        }

        JsonNode stepsNode = jsonNode.path("steps");
        if (!stepsNode.isMissingNode()) {
            this.steps = Lists.newArrayList();
            for (JsonNode element : stepsNode) {
                this.steps.add(new InteractionComponent(element));
            }
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.name != null) {
            node.set("name", this.getName().toJsonNode(version));
        }
        if (this.description != null) {
            node.set("description", this.getDescription().toJsonNode(version));
        }
        if (this.type != null) {
            node.put("type", this.getType().toString());
        }
        if (this.moreInfo != null) {
            node.put("moreInfo", this.getMoreInfo().toString());
        }
        if (this.extensions != null) {
            node.set("extensions", this.getExtensions().toJsonNode(version));
        }
        if (this.interactionType != null) {
            node.put("interactionType", this.getInteractionType().toString());

            switch (this.interactionType) {
                case CHOICE:
                case SEQUENCING:
                    if (this.choices != null && this.choices.size() > 0) {
                        ArrayNode choices = JsonMapper.getInstance().createArrayNode();
                        for (InteractionComponent ic : this.getChoices()) {
                            choices.add(ic.toJsonNode(version));
                        }
                        node.set("choices", choices);
                    }
                    break;
                case LIKERT:
                    if (this.scale != null && this.scale.size() > 0) {
                        ArrayNode scale = JsonMapper.getInstance().createArrayNode();
                        for (InteractionComponent ic : this.getScale()) {
                            scale.add(ic.toJsonNode(version));
                        }
                        node.set("scale", scale);
                    }
                    break;
                case MATCHING:
                    if (this.source != null && this.source.size() > 0) {
                        ArrayNode source = JsonMapper.getInstance().createArrayNode();
                        for (InteractionComponent ic : this.getSource()) {
                            source.add(ic.toJsonNode(version));
                        }
                        node.set("source", source);
                    }
                    if (this.target != null && this.target.size() > 0) {
                        ArrayNode target = JsonMapper.getInstance().createArrayNode();
                        for (InteractionComponent ic : this.getTarget()) {
                            target.add(ic.toJsonNode(version));
                        }
                        node.set("target", target);
                    }
                    break;
                case PERFORMANCE:
                    if (this.steps != null && this.steps.size() > 0) {
                        ArrayNode steps = JsonMapper.getInstance().createArrayNode();
                        for (InteractionComponent ic : this.getSteps()) {
                            steps.add(ic.toJsonNode(version));
                        }
                        node.set("steps", steps);
                    }
                    break;
                case TRUE_FALSE:
                case FILL_IN:
                case LONG_FILL_IN:
                case NUMERIC:
                case OTHER:
                    break;
            }
        }
        if (this.correctResponsesPattern != null && this.correctResponsesPattern.size() > 0) {
            ArrayNode responses = JsonMapper.getInstance().createArrayNode();
            for (String resp : this.getCorrectResponsesPattern()) {
                responses.add(resp);
            }
            node.set("correctResponsesPattern", responses);
        }

        return node;
    }

}
