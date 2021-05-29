package cn.elvea.platform.commons.web;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * JsonController
 *
 * @author elvea
 * @since 0.0.1
 */
public class ParamControllerTests extends CommonsTestApplicationTests {

    @Test
    public void baseTests() throws Exception {
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.post("/param")
                .param("paramDto.id", "1")
                .param("paramDto.type", "2"))
                .andExpect(status().isOk())
                .andReturn();
    }

}
