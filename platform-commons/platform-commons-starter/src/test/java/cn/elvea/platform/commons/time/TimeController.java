package cn.elvea.platform.commons.time;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.json.jackson.CustomJsonFormat;
import cn.elvea.platform.commons.time.format.CustomDateTimeFormat;
import cn.elvea.platform.commons.web.Response;
import cn.elvea.platform.commons.web.controller.AbstractController;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 时间相关测试控制器
 *
 * @author elvea
 * @since 0.0.1
 */
@RestController
@RequestMapping("/time")
public class TimeController extends AbstractController {

    @PostMapping
    public Response<?> postTest(TimeParam param) {
        return Response.success(param);
    }

    @PostMapping("/json")
    public Response<?> postJsonTest(@RequestBody TimeParam param) {
        return Response.success(param);
    }

    @Data
    public static class TimeParam {

        @CustomJsonFormat(timeZoneConvert = true)
        @CustomDateTimeFormat(timeZoneConvert = true)
        private Date defaultDate;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.DATE)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.DATE)
        private Date date;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.DATE_TIME)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.DATE_TIME)
        private Date dateTime;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.SIMPLE_DATE_TIME)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.SIMPLE_DATE_TIME)
        private Date simpleDateTime;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.FULL_DATE_TIME)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.FULL_DATE_TIME)
        private Date fullDateTime;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.DATE)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.DATE)
        private LocalDate localDate;

        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.TIME)
        private LocalTime localTime;

        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.SIMPLE_TIME)
        private LocalTime simpleLocalTime;

        @CustomJsonFormat(patten = DateTimeFormat.Pattern.DATE_TIME, timeZoneConvert = true)
        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.DATE_TIME, timeZoneConvert = true)
        private LocalDateTime localDateTime;

        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.FULL_DATE_TIME, timeZoneConvert = true)
        private LocalDateTime fullLocalDateTime;

        @CustomDateTimeFormat(patten = DateTimeFormat.Pattern.SIMPLE_DATE_TIME, timeZoneConvert = true)
        private LocalDateTime simpleLocalDateTime;

    }

}
