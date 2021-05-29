package cn.elvea.platform.core.security;

import cn.elvea.platform.BaseWebTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static cn.elvea.platform.core.security.jwt.JwtSecurityConstants.AUTH_TOKEN_PATH;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 登录认证相关测试
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class AuthTests extends BaseWebTests {

    @Test
    public void passwordAuthTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(AUTH_TOKEN_PATH)
                .param("grant_type", "password")
                .param("username", "admin")
                .param("password", "admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isNotEmpty())
                .andExpect(jsonPath("$.refresh_token").isNotEmpty())
                .andReturn();
    }

    @Test
    public void captchaAuthTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(AUTH_TOKEN_PATH)
                .param("grant_type", "captcha")
                .param("username", "admin")
                .param("captcha_key", "admin")
                .param("captcha", "888888"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").isNotEmpty())
                .andExpect(jsonPath("$.refresh_token").isNotEmpty())
                .andReturn();
    }

}
