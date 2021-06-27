package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

/**
 * Attachment
 *
 * @author elveas
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Attachment implements AbstractJsonObject {
    /**
     *
     */
    private URI usageType;
    /**
     *
     */
    private LanguageMap display;
    /**
     *
     */
    private LanguageMap description;
    /**
     *
     */
    private String contentType;
    /**
     *
     */
    private Integer length;
    /**
     *
     */
    private String sha2;
    /**
     *
     */
    private URL fileUrl;
    /**
     *
     */
    private byte[] content;

    public Attachment(JsonNode jsonNode) throws URISyntaxException, MalformedURLException, IOException, NoSuchAlgorithmException {
        this(jsonNode, null);
    }

    public Attachment(JsonNode jsonNode, byte[] content) throws URISyntaxException, MalformedURLException, IOException, NoSuchAlgorithmException {
        JsonNode usageTypeNode = jsonNode.path("usageType");
        if (!usageTypeNode.isMissingNode()) {
            this.setUsageType(new URI(usageTypeNode.textValue()));
        }

        JsonNode displayNode = jsonNode.path("display");
        if (!displayNode.isMissingNode()) {
            this.setDisplay(new LanguageMap(displayNode));
        }

        JsonNode descriptionNode = jsonNode.path("description");
        if (!descriptionNode.isMissingNode()) {
            this.setDescription(new LanguageMap(descriptionNode));
        }

        JsonNode contentTypeNode = jsonNode.path("contentType");
        if (!contentTypeNode.isMissingNode()) {
            this.setContentType(contentTypeNode.textValue());
        }

        JsonNode lengthNode = jsonNode.path("length");
        if (!lengthNode.isMissingNode()) {
            this.setLength(lengthNode.intValue());
        }

        JsonNode sha2Node = jsonNode.path("sha2");
        if (!sha2Node.isMissingNode()) {
            this.setSha2(sha2Node.textValue());
        }

        JsonNode fileUrlNode = jsonNode.path("fileUrl");
        if (!fileUrlNode.isMissingNode()) {
            this.setFileUrl(new URL(fileUrlNode.textValue()));
        }

        if (content != null) {
            this.setContent(content);
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        if (this.getUsageType() != null) {
            node.put("usageType", this.getUsageType().toString());
        }
        if (this.getDisplay() != null) {
            node.set("display", this.getDisplay().toJsonNode(version));
        }
        if (this.getDescription() != null) {
            node.set("description", this.getDescription().toJsonNode(version));
        }
        if (this.getContentType() != null) {
            node.put("contentType", this.getContentType());
        }
        if (this.getLength() != null) {
            node.put("length", this.getLength());
        }
        if (this.getSha2() != null) {
            node.put("sha2", this.getSha2());
        }
        if (this.getFileUrl() != null) {
            node.put("fileUrl", this.getFileUrl().toString());
        }

        return node;
    }

}
