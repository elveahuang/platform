package cn.elvea.platform.system.configuration;

import cn.elvea.platform.commons.core.logging.store.LogStore;
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
     * 日志存储
     */
    @Bean
    public LogStore logStore(LogApi logApi) {
        return logApi::saveOperationLog;
    }

    /**
     * 日志拦截器
     */
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

}
