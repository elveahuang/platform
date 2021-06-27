package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.entity.ActivityProfileEntity;
import cn.elvea.platform.xapi.service.ActivityProfileService;
import cn.elvea.platform.xapi.utils.XApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ActivityProfileServiceImpl
 *
 * @author elvea
 */
@Slf4j
@Service
public class ActivityProfileServiceImpl extends AbstractXApiService implements ActivityProfileService {

    /**
     * @see ActivityProfileService#getActivityProfile(String, String)
     */
    @Override
    public String getActivityProfile(String activityId, String profileId) {
        //
        Query query = new Query(this.createCriteria(activityId, profileId, null));
        //
        ActivityProfileEntity entity = this.mongoTemplate.findOne(query, ActivityProfileEntity.class);
        return entity != null ? entity.getContent() : "";
    }

    /**
     * @see ActivityProfileService#getActivityProfileList(String, String)
     */
    @Override
    public List<String> getActivityProfileList(String activityId, String since) {
        //
        Query query = new Query(this.createCriteria(activityId, null, since));
        //
        List<ActivityProfileEntity> entityList = this.mongoTemplate.find(query, ActivityProfileEntity.class);
        return entityList.stream().map(ActivityProfileEntity::getProfileId).collect(Collectors.toList());
    }

    /**
     * @see ActivityProfileService#saveActivityProfile(String, String, String)
     */
    @Override
    public void saveActivityProfile(String activityId, String profileId, String content) {
        //
        Query query = new Query(this.createCriteria(activityId, profileId, null));
        //
        ActivityProfileEntity entity = this.mongoTemplate.findOne(query, ActivityProfileEntity.class);
        if (entity != null) {
            entity.setContent(content);
            entity.setUpdatedAt(new Date());
            entity.setActive(Boolean.TRUE);
        } else {
            entity = new ActivityProfileEntity();
            entity.setActivityId(activityId);
            entity.setProfileId(profileId);
            entity.setContent(content);
            entity.setCreatedAt(new Date());
            entity.setUpdatedAt(new Date());
            entity.setActive(Boolean.TRUE);
        }
        this.activityProfileRepository.save(entity);
    }

    /**
     * @see ActivityProfileService#deleteActivityProfile(String, String)
     */
    @Override
    public void deleteActivityProfile(String activityId, String profileId) {
        //
        Query query = new Query(this.createCriteria(activityId, profileId, null));
        //
        ActivityProfileEntity entity = this.mongoTemplate.findOne(query, ActivityProfileEntity.class);
        if (entity != null) {
            this.activityProfileRepository.delete(entity);
        }
    }

    /**
     * 私有方法用于构建查询条件
     */
    private Criteria createCriteria(String activityId, String profileId, String since) {
        Criteria criteria = new Criteria();
        //
        if (StringUtils.isNotEmpty(activityId)) {
            criteria.and("activityId").is(activityId);
        }
        //
        if (StringUtils.isNotEmpty(profileId)) {
            criteria.and("profileId").is(profileId);
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

