package cn.elvea.platform.xapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ActivityProfile
 *
 * @author elvea
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ActivityProfile implements Serializable {
    /**
     *
     */
    private String activityId;
    /**
     *
     */
    private String profileId;
    /**
     *
     */
    private String content;
}
