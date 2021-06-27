package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * XApiOAuthController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/OAuth")
@Tag(name = "XApi OAuth Resource")
public class XApiOAuthController extends XApiAbstractController {

    /**
     * Temporary Credential Request
     */
    @RequestMapping("initiate")
    @ResponseBody
    public XApiResponse<?> initiate() {
        return XApiResponse.success(this.aboutService.about());
    }

    /**
     * Resource Owner Authorization
     */
    @RequestMapping("authorize")
    @ResponseBody
    public XApiResponse<?> authorize() {
        return XApiResponse.success(this.aboutService.about());
    }

    /**
     * Token Request
     */
    @RequestMapping("token")
    @ResponseBody
    public XApiResponse<?> token() {
        return XApiResponse.success(this.aboutService.about());
    }

}
