package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

import java.net.URISyntaxException;

/**
 * Result
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Result implements AbstractJsonObject {
    /**
     *
     */
    private Score score;
    /**
     *
     */
    private Boolean success;
    /**
     *
     */
    private Boolean completion;
    /**
     *
     */
    private Period duration;
    /**
     *
     */
    private String response;
    /**
     *
     */
    private Extensions extensions;

    public Result(JsonNode jsonNode) throws URISyntaxException {
        this();

        JsonNode scoreNode = jsonNode.path("score");
        if (!scoreNode.isMissingNode()) {
            this.setScore(new Score(scoreNode));
        }

        JsonNode successNode = jsonNode.path("success");
        if (!successNode.isMissingNode()) {
            this.setSuccess(successNode.booleanValue());
        }

        JsonNode completionNode = jsonNode.path("completion");
        if (!completionNode.isMissingNode()) {
            this.setCompletion(completionNode.booleanValue());
        }

        JsonNode durationNode = jsonNode.path("duration");
        if (!durationNode.isMissingNode()) {
            this.setDuration(new Period(durationNode.textValue()));
        }

        JsonNode responseNode = jsonNode.path("response");
        if (!responseNode.isMissingNode()) {
            this.setResponse(responseNode.textValue());
        }

        JsonNode extensionsNode = jsonNode.path("extensions");
        if (!extensionsNode.isMissingNode()) {
            this.setExtensions(new Extensions(extensionsNode));
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.score != null) {
            node.set("score", this.getScore().toJsonNode(version));
        }
        if (this.success != null) {
            node.put("success", this.getSuccess());
        }
        if (this.completion != null) {
            node.put("completion", this.getCompletion());
        }
        if (this.duration != null) {
            String shortenedDuration = ISOPeriodFormat.standard().print(this.getDuration()).replaceAll("(\\.\\d\\d)\\dS", "$1S");
            node.put("duration", shortenedDuration);
        }
        if (this.response != null) {
            node.put("response", this.getResponse());
        }
        if (this.extensions != null) {
            node.set("extensions", this.getExtensions().toJsonNode(version));
        }
        return node;
    }

}
