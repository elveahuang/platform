package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * XApiActivityController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/activites")
@Tag(name = "XApi Activites Resource")
public class XApiActivityController extends XApiAbstractController {

    @GetMapping
    @ResponseBody
    public XApiResponse<?> getActivities(@RequestParam("activityId") String activityId) {
        return XApiResponse.success(this.activityService.getActivities(activityId));
    }

}
