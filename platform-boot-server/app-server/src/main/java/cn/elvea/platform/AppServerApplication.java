package cn.elvea.platform;

import cn.elvea.platform.commons.constants.GlobalConstants;
import cn.elvea.platform.commons.data.jpa.repository.BaseEntityRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import me.ahoo.cosid.spring.boot.starter.actuate.CosIdEndpointAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {
        "cn.elvea.platform.configuration",
        "cn.elvea.platform.commons",
        "cn.elvea.platform.lxp",
        "cn.elvea.platform.security",
        "cn.elvea.platform.system",
}, exclude = {
        QuartzAutoConfiguration.class,
        CosIdEndpointAutoConfiguration.class
})
@EntityScan(basePackages = {
        "cn.elvea.platform.lxp.**.entity",
        "cn.elvea.platform.system.**.entity",
})
@EnableJpaRepositories(basePackages = {
        "cn.elvea.platform.lxp.**.repository",
        "cn.elvea.platform.system.**.repository",
}, repositoryBaseClass = BaseEntityRepositoryImpl.class)
public class AppServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppServerApplication.class, args);
        Environment env = context.getEnvironment();
        log.info("Application {} started. ", env.getProperty("spring.application.name", ""));
        log.info("Application version - {}. ", GlobalConstants.VERSION);
    }

}
