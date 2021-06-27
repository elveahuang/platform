package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_AGENT;

/**
 * Person
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person implements AbstractJsonObject {
    /**
     *
     */
    private String objectType = "Person";
    /**
     *
     */
    private List<String> name;
    /**
     *
     */
    private List<String> mbox;
    /**
     *
     */
    private List<String> mbox_sha1sum;
    /**
     *
     */
    private List<String> openid;
    /**
     *
     */
    private List<Account> account;

    public Person(String json) {
        this(JsonMapper.toJsonNode(json));
    }

    public Person(JsonNode jsonNode) {
        JsonNode nameNode = jsonNode.path("name");
        if (!nameNode.isMissingNode()) {
            this.name = Lists.newLinkedList();
            this.name.add(nameNode.textValue());
        }

        JsonNode mboxNode = jsonNode.path("mbox");
        if (!mboxNode.isMissingNode()) {
            this.mbox = Lists.newLinkedList();
            this.mbox.add(mboxNode.textValue());
        }

        JsonNode mboxSHA1SumNode = jsonNode.path("mbox_sha1sum");
        if (!mboxSHA1SumNode.isMissingNode()) {
            this.mbox_sha1sum = Lists.newLinkedList();
            this.mbox_sha1sum.add(mboxSHA1SumNode.textValue());
        }

        JsonNode openIDNode = jsonNode.path("openid");
        if (!openIDNode.isMissingNode()) {
            this.openid = Lists.newLinkedList();
            this.openid.add(openIDNode.textValue());
        }

        JsonNode acctNode = jsonNode.path("account");
        if (!acctNode.isMissingNode()) {
            this.account = Lists.newLinkedList();
            this.account.add(new Account(acctNode));
        }
    }

    public static Person fromJsonNode(@NotNull JsonNode jsonNode) {
        String objectType = OBJECT_TYPE_AGENT;
        JsonNode objectTypeNode = jsonNode.path("objectType");
        if (!objectTypeNode.isMissingNode()) {
            objectType = objectTypeNode.textValue();
        }
        return new Person(jsonNode);
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();

        node.put("objectType", this.getObjectType());

        if (this.name != null && this.name.size() > 0) {
            ArrayNode name = JsonMapper.getInstance().createArrayNode();
            for (String element : this.getName()) {
                name.add(element);
            }
            node.set("name", name);
        }

        if (this.mbox != null && this.mbox.size() > 0) {
            ArrayNode mbox = JsonMapper.getInstance().createArrayNode();
            for (String element : this.getMbox()) {
                mbox.add(element);
            }
            node.set("mbox", mbox);
        }

        if (this.mbox_sha1sum != null && this.mbox_sha1sum.size() > 0) {
            ArrayNode mbox_sha1sum = JsonMapper.getInstance().createArrayNode();
            for (String element : this.getMbox_sha1sum()) {
                mbox_sha1sum.add(element);
            }
            node.set("mbox_sha1sum", mbox_sha1sum);
        }

        if (this.openid != null && this.openid.size() > 0) {
            ArrayNode openid = JsonMapper.getInstance().createArrayNode();
            for (String element : this.getOpenid()) {
                openid.add(element);
            }
            node.set("openid", openid);
        }

        if (this.account != null && this.account.size() > 0) {
            ArrayNode account = JsonMapper.getInstance().createArrayNode();
            for (Account element : this.getAccount()) {
                account.add(element.toJsonNode(version));
            }
            node.set("account", account);
        }

        return node;
    }
}
