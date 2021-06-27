package cn.elvea.platform.xapi.model;

import cn.elvea.platform.xapi.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * ActivityState
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ActivityState implements Serializable {
    /**
     *
     */
    private String activityId;
    /**
     *
     */
    private String stateId;
    /**
     *
     */
    private Agent agent;
    /**
     *
     */
    private UUID registration;
    /**
     *
     */
    private JsonObject state;
}
