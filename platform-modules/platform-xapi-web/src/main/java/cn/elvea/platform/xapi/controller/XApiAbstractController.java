package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制器
 *
 * @author elvea
 */
public abstract class XApiAbstractController {

    /**
     * ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * XAPIService
     */
    @Autowired
    protected AboutService aboutService;

    /**
     * AgentProfileService
     */
    @Autowired
    protected AgentProfileService agentProfileService;

    /**
     * ActivityService
     */
    @Autowired
    protected ActivityService activityService;

    /**
     * ActivityProfileService
     */
    @Autowired
    protected ActivityProfileService activityProfileService;

    /**
     * ActivityStateService
     */
    @Autowired
    protected ActivityStateService activityStateService;

    /**
     * StatementService
     */
    @Autowired
    protected StatementService statementService;

}
