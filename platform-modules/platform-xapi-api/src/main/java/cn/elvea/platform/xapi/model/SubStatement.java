package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.enums.XApiVersionEnum;
import cn.elvea.platform.xapi.json.JsonMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_SUBSTATEMENT;

/**
 * SubStatement
 *
 * @author elveas
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SubStatement extends AbstractStatement implements AbstractObject {

    /**
     *
     */
    private final String objectType = OBJECT_TYPE_SUBSTATEMENT;

    public SubStatement(JsonNode jsonNode) throws MalformedURLException, URISyntaxException, IOException, NoSuchAlgorithmException {
        super(jsonNode);
    }

    public SubStatement(String json) throws Exception {
        super(JsonMapper.toJsonNode(json));
    }

    /**
     * @see AbstractJsonObject#toJsonNode(XApiVersionEnum)
     */
    @Override
    public ObjectNode toJsonNode(XApiVersionEnum version) {
        ObjectNode node = super.toJsonNode(version);
        node.put("objectType", this.getObjectType());
        return node;
    }

}
