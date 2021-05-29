package cn.elvea.platform.commons.logging.provider;

import cn.elvea.platform.commons.logging.dto.OptLogDto;
import cn.elvea.platform.commons.utils.CollectionUtils;
import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 日志服务管理器
 *
 * @author elvea
 * @since 0.0.1
 */
public class LoggingProviderManager {

    private List<LoggingProvider> providers;

    public LoggingProviderManager() {
        providers = Lists.newArrayList();
    }

    public LoggingProviderManager(List<LoggingProvider> providers) {
        this.providers = providers;
    }

    @Async
    public void saveLog(OptLogDto dto) {
        if (CollectionUtils.isNotEmpty(this.providers)) {
            for (LoggingProvider provider : this.providers) {
                provider.saveLog(dto);
            }
        }
    }

    /**
     * 作为第一个日志服务
     */
    public void first(LoggingProvider loggingProvider) {
        this.providers.add(0, loggingProvider);
    }

    /**
     * 作为最后一个日志服务
     */
    public void last(LoggingProvider loggingProvider) {
        this.providers.add(loggingProvider);
    }

    /**
     * 只保留参数中的日志服务
     */
    public void only(LoggingProvider loggingProvider) {
        this.providers = Lists.newArrayList(loggingProvider);
    }

}
