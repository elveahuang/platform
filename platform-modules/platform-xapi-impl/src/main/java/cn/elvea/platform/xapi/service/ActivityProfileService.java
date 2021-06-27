package cn.elvea.platform.xapi.service;

import java.util.List;

/**
 * ActivityProfileService
 *
 * @author elvea
 */
public interface ActivityProfileService {

    /**
     * Get a single activity document
     */
    String getActivityProfile(String activityId, String profileId);

    /**
     * Get multiple activity document and return the available ids
     */
    List<String> getActivityProfileList(String activityId, String since);

    /**
     * Put or Post
     */
    void saveActivityProfile(String activityId, String profileId, String content);

    /**
     * Delete single state
     */
    void deleteActivityProfile(String activityId, String profileId);

}
