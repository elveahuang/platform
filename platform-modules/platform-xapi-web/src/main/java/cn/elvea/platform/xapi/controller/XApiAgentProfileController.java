package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * XApiAgentProfileController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/agents/profile")
@Tag(name = "XApi Agents Profile Resource")
public class XApiAgentProfileController extends XApiAbstractController {

    @GetMapping
    @ResponseBody
    public XApiResponse<?> getAgentProfile(@RequestParam("agent") String agent,
                                           @RequestParam(name = "profileId", required = false) String profileId,
                                           @RequestParam(name = "since", required = false) String since) {
        if (StringUtils.isNotEmpty(profileId)) {
            return XApiResponse.success(this.agentProfileService.getAgentProfile(agent, profileId));
        } else {
            return XApiResponse.success(this.agentProfileService.getAgentProfileList(agent, since));
        }
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public XApiResponse<?> putAgentProfile(@RequestParam("agent") String agent,
                                        @RequestParam(name = "profileId", required = false, defaultValue = "") String profileId,
                                        @RequestBody String requestBody) {
        this.agentProfileService.saveAgentProfile(agent, profileId, requestBody);
        return XApiResponse.success();
    }

    @DeleteMapping
    @ResponseBody
    public XApiResponse<?> deleteAgentProfile(@RequestParam(name = "agent") String agent,
                                           @RequestParam(name = "profileId") String profileId) {
        this.agentProfileService.deleteAgentProfile(agent, profileId);
        return XApiResponse.success();
    }

}
