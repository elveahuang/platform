package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import cn.elvea.platform.xapi.json.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.Map;

/**
 * LanguageMap
 *
 * @author elvea
 */
public class LanguageMap implements AbstractJsonObject {

    private final Map<String, String> _map = Maps.newHashMap();

    public LanguageMap(JsonNode jsonNode) {
        Iterator<Map.Entry<String, JsonNode>> items = jsonNode.fields();
        while (items.hasNext()) {
            Map.Entry<String, JsonNode> item = items.next();
            this.put(item.getKey(), item.getValue().textValue());
        }
    }

    /**
     * @see JsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = JsonMapper.getInstance().createObjectNode();
        for (Map.Entry<String, String> entry : this._map.entrySet()) {
            node.put(entry.getKey(), entry.getValue());
        }
        return node;
    }

    public String put(String key, String val) {
        return this._map.put(key, val);
    }

    public String get(String key) {
        return this._map.get(key);
    }

    public boolean containsKey(String key) {
        return this._map.containsKey(key);
    }

    public boolean containsValue(String value) {
        return this._map.containsValue(value);
    }

}
