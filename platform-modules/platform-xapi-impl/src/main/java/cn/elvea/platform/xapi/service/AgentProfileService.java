package cn.elvea.platform.xapi.service;

import java.util.List;

/**
 * AgentProfileService
 *
 * @author elvea
 */
public interface AgentProfileService {

    /**
     * Get a single agent document
     */
    String getAgentProfile(String agent, String profileId);

    /**
     * Get multiple agent document and return the available ids
     */
    List<String> getAgentProfileList(String agent, String since);

    /**
     * Put or Post
     */
    void saveAgentProfile(String agent, String profileId, String content);

    /**
     * Delete agent profile
     */
    void deleteAgentProfile(String agent, String profileId);

}
