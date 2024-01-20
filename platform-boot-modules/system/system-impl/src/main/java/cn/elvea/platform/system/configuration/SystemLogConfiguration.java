package cn.elvea.platform.system.configuration;

import cn.elvea.platform.commons.core.extensions.log.LogCustomizer;
import cn.elvea.platform.system.commons.interceptor.LogInterceptor;
import cn.elvea.platform.system.log.api.LogApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration
public class SystemLogConfiguration {

    /**
     * 自定义日志存储，用于保存操作记录
     */
    @Bean
    public LogCustomizer logManagerCustomizer(LogApi logApi) {
        return configuration -> configuration.last(logApi::saveOperationLog);
    }

    /**
     * 创建日志拦截器
     */
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

}
