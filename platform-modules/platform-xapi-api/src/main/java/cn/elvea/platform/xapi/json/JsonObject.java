package cn.elvea.platform.xapi.json;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * XApiJsonObject
 *
 * @author elvea
 */
public interface JsonObject {

    /**
     * toJsonNode
     *
     * @param version {@link XApiVersionEnum}
     * @return {@link ObjectNode}
     */
    ObjectNode toJsonNode(XApiVersionEnum version);

    /**
     * toJsonNode
     *
     * @return {@link ObjectNode}
     */
    ObjectNode toJsonNode();

    /**
     * toJson
     *
     * @param version {@link XApiVersionEnum}
     * @param pretty  是否格式化
     * @return json
     */
    String toJson(XApiVersionEnum version, Boolean pretty);

    /**
     * toJson
     *
     * @param version {@link XApiVersionEnum}
     * @return json
     */
    String toJson(XApiVersionEnum version);

    /**
     * toJson
     *
     * @param pretty 是否格式化
     * @return json
     */
    String toJson(Boolean pretty);

    /**
     * toJson
     *
     * @return json
     */
    String toJson();

}
