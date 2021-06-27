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
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static cn.elvea.platform.xapi.utils.XApiConstants.*;

/**
 * XApiStatement
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class AbstractStatement implements AbstractJsonObject {
    /**
     *
     */
    protected Actor actor;
    /**
     *
     */
    protected Verb verb;
    /**
     *
     */
    protected AbstractObject object;
    /**
     *
     */
    protected Result result;
    /**
     *
     */
    protected Context context;
    /**
     *
     */
    protected DateTime timestamp;
    /**
     *
     */
    protected List<Attachment> attachments;

    public AbstractStatement(@NotNull String json) throws IOException, URISyntaxException, NoSuchAlgorithmException {
        this(JsonMapper.toJsonNode(json));
    }

    public AbstractStatement(@NotNull JsonNode jsonNode) throws URISyntaxException, MalformedURLException, IOException, NoSuchAlgorithmException {
        this();

        JsonNode actorNode = jsonNode.path("actor");
        if (!actorNode.isMissingNode()) {
            this.setActor(Agent.fromJsonNode(actorNode));
        }

        JsonNode verbNode = jsonNode.path("verb");
        if (!verbNode.isMissingNode()) {
            this.setVerb(new Verb(verbNode));
        }

        JsonNode objectNode = jsonNode.path("object");
        if (!objectNode.isMissingNode()) {
            String objectType = objectNode.path("objectType").textValue();
            if (OBJECT_TYPE_AGENT.equals(objectType) || OBJECT_TYPE_GROUP.equals(objectType)) {
                this.setObject(Agent.fromJsonNode(objectNode));
            } else if (OBJECT_TYPE_STATEMENT_REF.equals(objectType)) {
                this.setObject(new StatementRef(objectNode));
            } else if (OBJECT_TYPE_SUBSTATEMENT.equals(objectType)) {
                this.setObject(new SubStatement(objectNode));
            } else {
                this.setObject(new Activity(objectNode));
            }
        }

        JsonNode resultNode = jsonNode.path("result");
        if (!resultNode.isMissingNode()) {
            this.setResult(new Result(resultNode));
        }

        JsonNode contextNode = jsonNode.path("context");
        if (!contextNode.isMissingNode()) {
            this.setContext(new Context(contextNode));
        }

        JsonNode timestampNode = jsonNode.path("timestamp");
        if (!timestampNode.isMissingNode()) {
            this.setTimestamp(new DateTime(timestampNode.textValue()));
        }

        JsonNode attachmentsNode = jsonNode.path("attachments");
        if (!attachmentsNode.isMissingNode()) {
            this.attachments = Lists.newArrayList();
            for (JsonNode element : attachmentsNode) {
                this.attachments.add(new Attachment(element));
            }
        }

    }

    public AbstractStatement(Agent actor, Verb verb, AbstractObject object, Result result, Context context) {
        this();

        this.setActor(actor);
        this.setVerb(verb);
        this.setObject(object);
        this.setResult(result);
        this.setContext(context);
    }

    public AbstractStatement(Agent actor, Verb verb, AbstractObject object) {
        this(actor, verb, object, null, null);
    }

    /**
     * @see AbstractJsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime().withZoneUTC();

        node.set("actor", this.getActor().toJsonNode(version));
        node.set("verb", this.getVerb().toJsonNode(version));
        node.set("object", this.getObject().toJsonNode(version));
        if (this.result != null) {
            node.set("result", this.getResult().toJsonNode(version));
        }
        if (this.context != null) {
            node.set("context", this.getContext().toJsonNode(version));
        }
        if (this.timestamp != null) {
            node.put("timestamp", fmt.print(this.getTimestamp()));
        }
        if (this.getAttachments() != null && this.getAttachments().size() > 0) {
            ArrayNode attachmentsNode = JsonMapper.getInstance().createArrayNode();
            for (Attachment attachment : this.getAttachments()) {
                attachmentsNode.add(attachment.toJsonNode(version));
            }
            node.set("attachments", attachmentsNode);
        }

        return node;
    }

}
