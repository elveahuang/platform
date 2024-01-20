package cn.elvea.platform.configuration;

import cn.elvea.platform.commons.core.constants.GlobalConstants;
import cn.elvea.platform.lxp.commons.constants.LxpConstants;
import cn.elvea.platform.system.commons.constants.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@EnableAspectJAutoProxy
@Configuration(proxyBeanMethods = false)
public class ApplicationConfiguration {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                SystemConstants.I18N_SECURITY_MESSAGES,
                SystemConstants.I18N_LABEL_MESSAGES,
                SystemConstants.I18N_VALIDATION_MESSAGES,
                LxpConstants.I18N_LABEL_MESSAGES,
                LxpConstants.I18N_VALIDATION_MESSAGES
        );
        messageSource.setDefaultEncoding(GlobalConstants.ENCODING);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
