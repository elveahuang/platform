package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

/**
 * Statement
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Statement extends AbstractStatement {
    /**
     * ID
     */
    private String id;
    /**
     * Stored
     */
    private DateTime stored;
    /**
     * Authority
     */
    private Agent authority;
    /**
     * Version
     */
    private String version;

    public Statement(@NotNull String json) throws IOException, URISyntaxException, NoSuchAlgorithmException {
        this(JsonMapper.toJsonNode(json));
    }

    public Statement(@NotNull JsonNode jsonNode) throws IOException, URISyntaxException, NoSuchAlgorithmException {
        super(jsonNode);

        JsonNode idNode = jsonNode.path("id");
        if (!idNode.isMissingNode()) {
            this.setId(idNode.textValue());
        }

        JsonNode storedNode = jsonNode.path("stored");
        if (!storedNode.isMissingNode()) {
            this.setTimestamp(new DateTime(storedNode.textValue()));
        }

        JsonNode authorityNode = jsonNode.path("authority");
        if (!authorityNode.isMissingNode()) {
            this.setAuthority((Agent) Actor.fromJsonNode(authorityNode));
        }

        JsonNode versionNode = jsonNode.path("version");
        if (!versionNode.isMissingNode()) {
            this.setVersion(versionNode.textValue());
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = super.toJsonNode(version);
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime().withZoneUTC();
        if (this.id != null) {
            node.put("id", this.getId());
        }
        if (this.stored != null) {
            node.put("stored", fmt.print(this.getStored()));
        }
        if (this.authority != null) {
            node.set("authority", this.getAuthority().toJsonNode(version));
        }
        return node;
    }

    public static List<Statement> fromJson(String json) throws IOException, URISyntaxException, NoSuchAlgorithmException {
        List<Statement> statements = Lists.newArrayList();
        JsonNode jsonNode = JsonMapper.toJsonNode(json);
        if (jsonNode != null && jsonNode.isArray()) {
            Iterator<JsonNode> iterator = jsonNode.iterator();
            while (iterator.hasNext()) {
                statements.add(new Statement(iterator.next()));
            }
        } else if (jsonNode != null) {
            statements.add(new Statement(jsonNode));
        }
        return statements;
    }

}
