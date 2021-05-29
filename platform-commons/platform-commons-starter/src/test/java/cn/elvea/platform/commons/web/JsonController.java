package cn.elvea.platform.commons.web;

import org.springframework.web.bind.annotation.*;

/**
 * JsonController
 *
 * @author elvea
 * @since 0.0.1
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @GetMapping
    public Response<JsonObject> test() {
        return Response.success();
    }

    @PostMapping
    public Response<JsonObject> port(@RequestBody JsonObject object) {
        return Response.success(object);
    }

}
