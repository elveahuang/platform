package cn.elvea.platform.xapi.model;

import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.exception.InvalidRequestException;
import cn.elvea.platform.xapi.json.JsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_AGENT;
import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_GROUP;

/**
 * XApiObject
 *
 * @author elvea
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Actor implements AbstractObject {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String mbox;
    /**
     *
     */
    private String mboxSHA1Sum;
    /**
     *
     */
    private String openID;
    /**
     *
     */
    private Account account;

    public Actor(JsonNode jsonNode) {
        JsonNode nameNode = jsonNode.path("name");
        if (!nameNode.isMissingNode()) {
            this.setName(nameNode.textValue());
        }

        JsonNode mboxNode = jsonNode.path("mbox");
        if (!mboxNode.isMissingNode()) {
            this.setMbox(mboxNode.textValue());
        }

        JsonNode mboxSHA1SumNode = jsonNode.path("mbox_sha1sum");
        if (!mboxSHA1SumNode.isMissingNode()) {
            this.setMboxSHA1Sum(mboxSHA1SumNode.textValue());
        }

        JsonNode openIDNode = jsonNode.path("openid");
        if (!openIDNode.isMissingNode()) {
            this.setOpenID(openIDNode.textValue());
        }

        JsonNode acctNode = jsonNode.path("account");
        if (!acctNode.isMissingNode()) {
            this.setAccount(new Account(acctNode));
        }
    }

    /**
     * @see AbstractObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        node.put("objectType", this.getObjectType());
        if (StringUtils.isNotEmpty(this.getName())) {
            node.put("name", this.getName());
        }
        if (StringUtils.isNotEmpty(this.getMbox())) {
            node.put("mbox", this.getMbox());
        }
        if (StringUtils.isNotEmpty(this.getMboxSHA1Sum())) {
            node.put("mbox_sha1sum", this.getMboxSHA1Sum());
        }
        if (StringUtils.isNotEmpty(this.getOpenID())) {
            node.put("openid", this.getOpenID());
        }
        if (this.getAccount() != null) {
            node.set("account", this.getAccount().toJsonNode(version));
        }
        return node;
    }

    public static Actor fromJson(@NotNull String json) throws InvalidRequestException {
        JsonNode jsonNode = null;
        try {
            jsonNode = JsonMapper.toJsonNode(json);
        } catch (Exception e) {
            log.error("failed to parse agent json [{}].", json);
            throw new InvalidRequestException();
        }
        return fromJsonNode(jsonNode);
    }

    public static Actor fromJsonNode(@NotNull JsonNode jsonNode) {
        String objectType = OBJECT_TYPE_AGENT;
        JsonNode objectTypeNode = jsonNode.path("objectType");
        if (!objectTypeNode.isMissingNode()) {
            objectType = objectTypeNode.textValue();
        }
        return OBJECT_TYPE_GROUP.equals(objectType) ? new Group(jsonNode) : new Agent(jsonNode);
    }

}
