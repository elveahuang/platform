package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.exception.XAPIException;
import cn.elvea.platform.xapi.model.Agent;
import cn.elvea.platform.xapi.repository.ActivityProfileRepository;
import cn.elvea.platform.xapi.repository.ActivityStateRepository;
import cn.elvea.platform.xapi.repository.AgentProfileRepository;
import cn.elvea.platform.xapi.repository.StatementRepository;
import cn.elvea.platform.xapi.utils.XApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * AbstractXApiService
 *
 * @author elvea
 */
public abstract class AbstractXApiService {
    /**
     * ActivityStateRepository
     */
    @Autowired
    protected ActivityStateRepository activityStateRepository;

    /**
     * ActivityStateRepository
     */
    @Autowired
    protected AgentProfileRepository agentProfileRepository;

    /**
     * ActivityProfileRepository
     */
    @Autowired
    protected ActivityProfileRepository activityProfileRepository;

    /**
     * StatementRepository
     */
    @Autowired
    protected StatementRepository statementRepository;

    /**
     * MongoTemplate
     */
    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * 处理Agent参数
     */
    public static void processAgentCriteria(Criteria criteria, String agentJson) throws XAPIException {
        Agent agent = (Agent) Agent.fromJson(agentJson);
        String uniqueIdentifier = XApiUtils.extractAgentUniqueIdentifier(agent);
        switch (uniqueIdentifier) {
            case "mbox":
                criteria.and("agent.mbox").is(agent.getMbox());
                break;
            case "mboxSHA1Sum":
                criteria.and("agent.mboxSHA1Sum").is(agent.getMboxSHA1Sum());
                break;
            case "openID":
                criteria.and("agent.openID").is(agent.getOpenID());
                break;
            case "account":
                criteria.and("agent.account.homePage").is(agent.getAccount().getHomePage());
                criteria.and("agent.account.name").is(agent.getAccount().getName());
                break;
            default:
                break;
        }
    }

}
