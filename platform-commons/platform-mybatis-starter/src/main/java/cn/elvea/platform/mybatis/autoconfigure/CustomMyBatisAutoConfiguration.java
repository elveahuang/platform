package cn.elvea.platform.mybatis.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
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
public class CustomMyBatisAutoConfiguration {

    // -----------------------------------------------------------------------------------------------------------------
    // MyBatis-Plus
    // -----------------------------------------------------------------------------------------------------------------

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * MyBatis自定义配置
     */
    @Bean
    @ConditionalOnMissingBean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

    /**
     * MyBatisPlus自定义配置
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return configuration -> {
        };
    }

}
