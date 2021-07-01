package cn.elvea.platform.core.security.jwt;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.core.security.SecurityUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

/**
 * JwtSecurityServiceTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class JwtSecurityServiceTests extends BaseTests {

    @Autowired
    private JwtSecurityService jwtSecurityService;

    @Test
    public void tokenTest() throws Exception {
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        SecurityUser user = new SecurityUser(1L, "root", "root", authorities);
        String token = jwtSecurityService.createAccessToken(user);
        jwtSecurityService.parseAccessToken(token);
    }

}
