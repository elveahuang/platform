package cn.elvea.platform.xapi.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static cn.elvea.platform.xapi.utils.XApiConstants.OBJECT_TYPE_AGENT;

/**
 * Agent
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Agent extends Actor {

    private String objectType = OBJECT_TYPE_AGENT;

    public Agent(JsonNode jsonNode) {
        super(jsonNode);
    }

}
