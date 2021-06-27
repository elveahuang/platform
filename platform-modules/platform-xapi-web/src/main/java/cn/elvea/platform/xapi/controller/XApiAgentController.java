package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import cn.elvea.platform.xapi.model.Person;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * XApiAgentController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/agents")
@Tag(name = "XApi Agents Resource")
public class XApiAgentController extends XApiAbstractController {

    @GetMapping
    @ResponseBody
    public XApiResponse<Person> getAgents(
            @RequestParam("agent") String agent) {
        return XApiResponse.success(new Person(agent));
    }

}
