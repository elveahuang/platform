package cn.elvea.platform.persistence.autoconfigure;

import cn.elvea.platform.persistence.mybatis.interceptor.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Custom MyBatis Auto Configuration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@MapperScan("cn.elvea.platform.persistence.mybatis.mapper")
public class CustomMyBatisAutoConfiguration {

    /**
     * MyBatis
     */
    @Bean
    @ConditionalOnClass(name = "org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer")
    @ConditionalOnMissingBean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.addInterceptor(new PaginationInterceptor());
    }

}
