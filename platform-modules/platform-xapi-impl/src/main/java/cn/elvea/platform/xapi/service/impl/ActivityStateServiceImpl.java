package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.entity.ActivityStateEntity;
import cn.elvea.platform.xapi.model.Actor;
import cn.elvea.platform.xapi.model.Agent;
import cn.elvea.platform.xapi.service.ActivityStateService;
import cn.elvea.platform.xapi.utils.XApiUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ActivityStateServiceImpl
 *
 * @author elvea
 * @see ActivityStateService
 */
@Service
public class ActivityStateServiceImpl extends AbstractXApiService implements ActivityStateService {

    /**
     * @see ActivityStateService#getActivityState(String, String, String, String)
     */
    @Override
    public String getActivityState(String activityId, String agentJson, String registration, String stateId) {
        Query query = new Query(createCriteria(activityId, stateId, agentJson, registration, null));
        ActivityStateEntity entity = this.mongoTemplate.findOne(query, ActivityStateEntity.class);
        return entity != null ? entity.getContent() : "";
    }

    /**
     * @see ActivityStateService#getActivityStateList(String, String, String, String)
     */
    @Override
    public List<String> getActivityStateList(String activityId, String agentJson, String registration, String since) {
        Query query = new Query(createCriteria(activityId, null, agentJson, registration, since));
        List<ActivityStateEntity> entityList = this.mongoTemplate.find(query, ActivityStateEntity.class);
        return entityList.stream().map(ActivityStateEntity::getStateId).collect(Collectors.toList());
    }

    /**
     * @see ActivityStateService#saveActivityState(String, String, String, String, String)
     */
    @Override
    public void saveActivityState(String activityId, String agentJson, String registration, String stateId, String content) {
        Query query = new Query(createCriteria(activityId, stateId, agentJson, registration, null));
        ActivityStateEntity entity = this.mongoTemplate.findOne(query, ActivityStateEntity.class);
        if (entity != null) {
            entity.setContent(content);
            entity.setUpdatedAt(new Date());
            entity.setActive(Boolean.TRUE);
        } else {
            entity = new ActivityStateEntity();
            entity.setStateId(stateId);
            entity.setActivityId(activityId);
            entity.setAgent((Agent) Actor.fromJson(agentJson));
            entity.setRegistration(registration);
            entity.setContent(content);
            entity.setCreatedAt(new Date());
            entity.setUpdatedAt(new Date());
            entity.setActive(Boolean.TRUE);
        }
        this.activityStateRepository.save(entity);
    }

    /**
     * @see ActivityStateService#deleteActivityState(String, String, String, String)
     */
    @Override
    public void deleteActivityState(String activityId, String agentJson, String stateId, String registration) {
        Query query = new Query(createCriteria(activityId, stateId, agentJson, registration, null));
        ActivityStateEntity entity = this.mongoTemplate.findOne(query, ActivityStateEntity.class);
        if (entity != null) {
            this.activityStateRepository.delete(entity);
        }
    }

    /**
     * @see ActivityStateService#deleteActivityStateList(String, String, String, String)
     */
    @Override
    public void deleteActivityStateList(String activityId, String agentJson, String registration, String since) {
        Query query = new Query(createCriteria(activityId, null, agentJson, registration, since));
        List<ActivityStateEntity> entityList = this.mongoTemplate.find(query, ActivityStateEntity.class);
        if (CollectionUtils.isNotEmpty(entityList)) {
            this.activityStateRepository.deleteAll(entityList);
        }
    }

    /**
     * 私有方法用于构建查询条件
     */
    private Criteria createCriteria(String activityId, String stateId, String agentJson, String registration, String since) {
        Criteria criteria = new Criteria();
        //
        if (StringUtils.isNotEmpty(activityId)) {
            criteria.and("activityId").is(activityId);
        }
        //
        if (StringUtils.isNotEmpty(stateId)) {
            criteria.and("stateId").is(stateId);
        }
        //
        processAgentCriteria(criteria, agentJson);
        //
        if (StringUtils.isNotEmpty(registration)) {
            criteria.and("registration").is(registration);
        }
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
