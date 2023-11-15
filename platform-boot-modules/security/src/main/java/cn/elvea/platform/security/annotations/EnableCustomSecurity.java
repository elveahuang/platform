package cn.elvea.platform.security.annotations;

import cn.elvea.platform.security.config.AuthorizationServerConfiguration;
import cn.elvea.platform.security.config.CommonSecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({
        AuthorizationServerConfiguration.class,
        CommonSecurityConfiguration.class,
})
public @interface EnableCustomSecurity {
}
