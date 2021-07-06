package cn.elvea.platform.core.security;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityUtilsTest
 *
 * @author elvea
 * @since 0.0.1
 */
public class SecurityUtilsTests extends BaseTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encryptPasswordTest() {
        System.out.println(this.passwordEncoder.encode("admin"));
    }

}
