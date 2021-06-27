package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * XApiActivityStateController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/activites/state")
@Tag(name = "XApi Activites State Resource")
public class XApiActivityStateController extends XApiAbstractController {

    /**
     * Get
     */
    @GetMapping
    @ResponseBody
    public XApiResponse<?> getActivityState(@RequestParam(name = "activityId") String activityId,
                                         @RequestParam(name = "agent") String agent,
                                         @RequestParam(name = "stateId", required = false) String stateId,
                                         @RequestParam(name = "registration", required = false) String registration,
                                         @RequestParam(name = "since", required = false) String since) {
        if (StringUtils.isNotEmpty(stateId)) {
            return XApiResponse.success(this.activityStateService.getActivityState(activityId, agent, registration, stateId));
        } else {
            return XApiResponse.success(this.activityStateService.getActivityStateList(activityId, agent, registration, since));
        }
    }

    /**
     * Put or Post
     */
    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public XApiResponse<?> saveActivityState(@RequestParam(name = "activityId") String activityId,
                                          @RequestParam(name = "agent") String agentJson,
                                          @RequestParam(name = "registration", required = false) String registration,
                                          @RequestParam(name = "stateId") String stateId,
                                          @RequestBody String bodyJson) {
        this.activityStateService.saveActivityState(activityId, agentJson, registration, stateId, bodyJson);
        return XApiResponse.success();
    }

    /**
     * Delete
     */
    @DeleteMapping
    @ResponseBody
    public XApiResponse<?> deleteActivityState(@RequestParam(name = "activityId") String activityId,
                                            @RequestParam(name = "agent") String agentJson,
                                            @RequestParam(name = "stateId", required = false) String stateId,
                                            @RequestParam(name = "registration", required = false) String registration,
                                            @RequestParam(name = "since", required = false) String since) {
        if (StringUtils.isNotEmpty(stateId)) {
            this.activityStateService.deleteActivityState(activityId, agentJson, stateId, registration);
        } else {
            this.activityStateService.deleteActivityStateList(activityId, agentJson, registration, since);
        }
        return XApiResponse.success();
    }

}
