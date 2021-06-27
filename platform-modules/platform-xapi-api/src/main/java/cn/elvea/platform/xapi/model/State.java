package cn.elvea.platform.xapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.net.URI;
import java.util.UUID;

/**
 * About
 *
 * @author elvea
 */
@Data
@NoArgsConstructor
public class State {
    /**
     *
     */
    private URI activityId;
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
    private String stateId;
    /**
     *
     */
    private DateTime updated;
}
