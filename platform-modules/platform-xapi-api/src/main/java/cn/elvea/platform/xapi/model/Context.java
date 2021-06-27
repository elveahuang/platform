package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Context
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Context implements AbstractJsonObject {
    /**
     *
     */
    private UUID registration;
    /**
     *
     */
    private Agent instructor;
    /**
     *
     */
    private Agent team;
    /**
     *
     */
    private ContextActivities contextActivities;
    /**
     *
     */
    private String revision;
    /**
     *
     */
    private String platform;
    /**
     *
     */
    private String language;
    /**
     *
     */
    private StatementRef statement;
    /**
     *
     */
    private Extensions extensions;

    public Context(JsonNode jsonNode) throws MalformedURLException, URISyntaxException {
        this();

        JsonNode registrationNode = jsonNode.path("registration");
        if (!registrationNode.isMissingNode()) {
            this.setRegistration(UUID.fromString(registrationNode.textValue()));
        }

        JsonNode instructorNode = jsonNode.path("instructor");
        if (!instructorNode.isMissingNode()) {
            this.setInstructor(new Agent(instructorNode));
        }

        JsonNode teamNode = jsonNode.path("team");
        if (!teamNode.isMissingNode()) {
            this.setTeam(new Agent(teamNode));
        }

        JsonNode contextActivitiesNode = jsonNode.path("contextActivities");
        if (!contextActivitiesNode.isMissingNode()) {
            this.setContextActivities(new ContextActivities(contextActivitiesNode));
        }

        JsonNode revisionNode = jsonNode.path("revision");
        if (!revisionNode.isMissingNode()) {
            this.setRevision(revisionNode.textValue());
        }

        JsonNode platformNode = jsonNode.path("platform");
        if (!platformNode.isMissingNode()) {
            this.setPlatform(platformNode.textValue());
        }

        JsonNode languageNode = jsonNode.path("language");
        if (!languageNode.isMissingNode()) {
            this.setLanguage(languageNode.textValue());
        }

        JsonNode statementNode = jsonNode.path("statement");
        if (!statementNode.isMissingNode()) {
            this.setStatement(new StatementRef(statementNode));
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

        if (this.registration != null) {
            node.put("registration", this.getRegistration().toString());
        }
        if (this.instructor != null) {
            node.set("instructor", this.getInstructor().toJsonNode(version));
        }
        if (this.team != null) {
            node.set("team", this.getTeam().toJsonNode(version));
        }
        if (this.contextActivities != null) {
            node.set("contextActivities", this.getContextActivities().toJsonNode(version));
        }
        if (this.revision != null) {
            node.put("revision", this.getRevision());
        }
        if (this.platform != null) {
            node.put("platform", this.getPlatform());
        }
        if (this.language != null) {
            node.put("language", this.getLanguage());
        }
        if (this.statement != null) {
            node.set("statement", this.getStatement().toJsonNode(version));
        }
        if (this.extensions != null) {
            node.set("extensions", this.getExtensions().toJsonNode(version));
        }

        return node;
    }

}
