package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * XApiActivityProfileController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/activities/profile")
@Tag(name = "XApi Activites Profile Resource")
public class XApiActivityProfileController extends XApiAbstractController {

    /**
     * @param activityId The Activity id associated with this Profile document.
     * @param profileId  The profile id associated with this Profile document.
     * @param since      Only ids of Profile documents stored since the specified Timestamp (exclusive) are returned.
     */
    @GetMapping
    @ResponseBody
    public XApiResponse<?> getActivityProfile(@RequestParam("activityId") String activityId,
                                              @RequestParam(name = "profileId", required = false) String profileId,
                                              @RequestParam(name = "since", required = false) String since) {
        if (StringUtils.isNotEmpty(profileId)) {
            return XApiResponse.success(this.activityProfileService.getActivityProfile(activityId, profileId));
        } else {
            return XApiResponse.success(this.activityProfileService.getActivityProfileList(activityId, since));
        }
    }

    /**
     * 新增或者更新
     */
    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public XApiResponse putActivityProfile(@RequestParam("activityId") String activityId,
                                           @RequestParam("profileId") String profileId,
                                           @RequestBody String requestBody) {
        this.activityProfileService.saveActivityProfile(activityId, profileId, requestBody);
        return XApiResponse.success();
    }

    /**
     * Delete
     */
    @DeleteMapping
    @ResponseBody
    public XApiResponse deleteActivityProfile(@RequestParam("activityId") String activityId,
                                              @RequestParam(name = "profileId") String profileId) {
        this.activityProfileService.deleteActivityProfile(activityId, profileId);
        return XApiResponse.success();
    }

}
