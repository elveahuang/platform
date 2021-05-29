package cn.elvea.platform.commons.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ParamController
 *
 * @author elvea
 * @since 0.0.1
 */
@RestController
@RequestMapping("/param")
public class ParamController {

    @PostMapping
    public Response<ParamDto> test(ParamDto paramDto) {
        return Response.success(paramDto);
    }

}
