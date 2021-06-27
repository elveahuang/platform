package cn.elvea.platform.xapi.model;

import cn.elvea.platform.commons.utils.StringUtils;
import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Account Object
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Account implements AbstractJsonObject {
    /**
     * 用户主页
     */
    private String homePage;
    /**
     * 用户唯一标识
     */
    private String name;

    public Account(String homePage, String name) {
        this.homePage = homePage;
        this.name = name;
    }

    public Account(JsonNode jsonNode) {
        JsonNode homePageNode = jsonNode.path("homePage");
        if (!homePageNode.isMissingNode()) {
            this.setHomePage(homePageNode.textValue());
        }
        JsonNode nameNode = jsonNode.path("name");
        if (!nameNode.isMissingNode()) {
            this.setName(nameNode.textValue());
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        if (StringUtils.isNotEmpty(this.homePage)) {
            node.put("homePage", this.getHomePage());
        }
        if (StringUtils.isNotEmpty(this.name)) {
            node.put("name", this.getName());
        }
        return node;
    }

}
