package cn.elvea.platform.commons.autoconfigure.data.core;

import cn.elvea.platform.commons.autoconfigure.data.core.properties.CustomDataProperties;
import cn.elvea.platform.commons.data.core.Dao;
import cn.elvea.platform.commons.data.core.dialect.DbDialect;
import cn.elvea.platform.commons.data.core.utils.JdbcUtils;
import cn.elvea.platform.commons.data.jpa.auditor.UserAuditorAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CustomDataProperties.class)
@ConditionalOnProperty(prefix = CustomDataProperties.PREFIX, name = "enabled", havingValue = "true")
public class CustomDataAutoConfiguration {

    public CustomDataAutoConfiguration() {
        log.info("CustomDataAutoConfiguration is enabled.");
    }

    /**
     * @see DbDialect
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomDataProperties.JDBC_PREFIX, name = "enabled", havingValue = "true")
    public DbDialect dbDialect(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.execute(JdbcUtils::getDialect);
    }

    /**
     * @see Dao
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomDataProperties.JDBC_PREFIX, name = "enabled", havingValue = "true")
    public Dao dao(JdbcTemplate jdbcTemplate) {
        return new Dao(jdbcTemplate);
    }

    /**
     * @see UserAuditorAware
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = CustomDataProperties.AUDITING_PREFIX, name = "enabled", havingValue = "true")
    public UserAuditorAware userAuditorAware() {
        return new UserAuditorAware();
    }

}
