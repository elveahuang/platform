package cn.elvea.platform.xapi.service.impl;

import cn.elvea.platform.xapi.model.Activity;
import cn.elvea.platform.xapi.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * ActivityServiceImpl
 *
 * @author elvea
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    /**
     * @see ActivityService#getActivities(String)
     */
    @Override
    public Activity getActivities(String activityId) {
        return new Activity(activityId);
    }

}
