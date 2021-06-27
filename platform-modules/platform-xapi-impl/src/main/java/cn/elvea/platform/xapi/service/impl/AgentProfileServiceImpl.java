package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.entity.AgentProfileEntity;
import cn.elvea.platform.xapi.exception.InvalidRequestException;
import cn.elvea.platform.xapi.service.AgentProfileService;
import cn.elvea.platform.xapi.utils.XApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AgentProfileServiceImpl
 *
 * @author elvea
 */
@Slf4j
@Service
public class AgentProfileServiceImpl extends AbstractXApiService implements AgentProfileService {

    /**
     * @see AgentProfileService#getAgentProfile(String, String)
     */
    @Override
    public String getAgentProfile(String agent, String profileId) {
        //
        Query query = new Query(this.createCriteria(agent, profileId, null));
        //
        AgentProfileEntity entity = this.mongoTemplate.findOne(query, AgentProfileEntity.class);
        return entity != null ? entity.getContent() : "";
    }

    /**
     * @see AgentProfileService#getAgentProfileList(String, String)
     */
    @Override
    public List<String> getAgentProfileList(String agent, String since) throws InvalidRequestException {
        //
        Query query = new Query(this.createCriteria(agent, null, since));
        //
        List<AgentProfileEntity> entityList = this.mongoTemplate.find(query, AgentProfileEntity.class);
        return entityList.stream().map(AgentProfileEntity::getProfileId).collect(Collectors.toList());
    }

    /**
     * @see AgentProfileService#saveAgentProfile(String, String, String)
     */
    @Override
    public void saveAgentProfile(String agent, String profileId, String content) {
        //
        Query query = new Query(this.createCriteria(agent, profileId, null));
        //
        AgentProfileEntity entity = this.mongoTemplate.findOne(query, AgentProfileEntity.class);
        if (entity != null) { // Update
            entity.setContent(content);
            entity.setActive(Boolean.TRUE);
        } else { // Create
            entity = new AgentProfileEntity();
            entity.setProfileId(profileId);
            entity.setAgent(XApiUtils.extractAgentObject(agent));
            entity.setContent(content);
            entity.setActive(Boolean.TRUE);
        }
        this.agentProfileRepository.save(entity);
    }

    /**
     * @see AgentProfileService#deleteAgentProfile(String, String)
     */
    @Override
    public void deleteAgentProfile(String agent, String profileId) {
        //
        Query query = new Query(this.createCriteria(agent, profileId, null));
        //
        List<AgentProfileEntity> entityList = this.mongoTemplate.find(query, AgentProfileEntity.class);
        if (CollectionUtils.isNotEmpty(entityList)) {
            this.agentProfileRepository.deleteAll(entityList);
        }
    }

    /**
     * 私有方法用于构建查询条件
     */
    private Criteria createCriteria(String agentJson, String profileId, String since) {
        Criteria criteria = new Criteria();
        //
        if (StringUtils.isNotEmpty(profileId)) {
            criteria.and("profileId").is(profileId);
        }
        //
        processAgentCriteria(criteria, agentJson);
        //
        if (StringUtils.isNotEmpty(since)) {
            Date sinceDateObject = XApiUtils.parseTimestamp(since);
            if (sinceDateObject != null) {
                criteria.and("createdAt").gt(sinceDateObject);
            }
        }
        return criteria;
    }

}
