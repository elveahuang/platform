package cn.elvea.platform.commons.time;

import cn.elvea.platform.base.constants.DateTimeFormat;
import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.utils.DateUtils;
import cn.elvea.platform.commons.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 时间日期单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class TimeControllerTests extends CommonsTestApplicationTests {

    @Test
    public void baseTests() throws Exception {
        Date now = new Date();
        MockHttpServletResponse resp = mockMvc.perform(MockMvcRequestBuilders.post("/time")
                .param("defaultDate", DateUtils.format(now, DateTimeFormat.Pattern.DATE_TIME))
                .param("date", DateUtils.format(now, DateTimeFormat.Pattern.DATE))
                .param("dateTime", DateUtils.format(now, DateTimeFormat.Pattern.DATE_TIME))
                .param("simpleDateTime", DateUtils.format(now, DateTimeFormat.Pattern.SIMPLE_DATE_TIME))
                .param("fullDateTime", DateUtils.format(now, DateTimeFormat.Pattern.FULL_DATE_TIME))
                .param("localDate", DateUtils.format(now, DateTimeFormat.Pattern.DATE))
                .param("localTime", DateUtils.format(now, DateTimeFormat.Pattern.TIME))
                .param("simpleLocalTime", DateUtils.format(now, DateTimeFormat.Pattern.SIMPLE_TIME))
                .param("localDateTime", DateUtils.format(now, DateTimeFormat.Pattern.DATE_TIME))
                .param("simpleLocalDateTime", DateUtils.format(now, DateTimeFormat.Pattern.SIMPLE_DATE_TIME))
                .param("fullLocalDateTime", DateUtils.format(now, DateTimeFormat.Pattern.FULL_DATE_TIME))
        ).andExpect(status().isOk()).andReturn().getResponse();
        System.out.println(resp.getContentAsString());
    }

    @Test
    public void jsonTests() throws Exception {
        Date now = new Date();
        Map<String, String> param = new HashMap<>();
        param.put("defaultDate", DateUtils.format(now, DateTimeFormat.Pattern.DATE_TIME));
        param.put("localDateTime", DateUtils.format(now, DateTimeFormat.Pattern.DATE_TIME));
        MockHttpServletResponse resp = mockMvc.perform(MockMvcRequestBuilders.post("/time/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(param))
        ).andExpect(status().isOk()).andReturn().getResponse();
        System.out.println(resp.getContentAsString());
    }

}
