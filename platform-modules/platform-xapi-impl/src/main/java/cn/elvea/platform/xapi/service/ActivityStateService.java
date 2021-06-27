package cn.elvea.platform.xapi.service;

import java.util.List;

/**
 * ActivityStateService
 *
 * @author elvea
 */
public interface ActivityStateService {

    /**
     * Get a single state document
     */
    String getActivityState(String activityId, String agentJson, String registration, String stateId);

    /**
     * Get multiple document and return the available ids
     */
    List<String> getActivityStateList(String activityId, String agentJson, String registration, String since);

    /**
     * Put or Post
     */
    void saveActivityState(String activityId, String agentJson, String registration, String stateId, String document);

    /**
     * Delete single state
     */
    void deleteActivityState(String activityId, String agentJson, String stateId, String registration);

    /**
     * Delete all state
     */
    void deleteActivityStateList(String activityId, String agentJson, String registration, String since);

}
