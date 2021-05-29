package cn.elvea.platform.commons.logging.provider;

/**
 * 自定义日志服务管理器
 *
 * @author elvea
 * @since 0.0.1
 */
@FunctionalInterface
public interface LoggingProviderManagerCustomizer {

    /**
     * 定制日志服务管理器
     *
     * @param manager {@link LoggingProviderManager}
     */
    void customize(LoggingProviderManager manager);

}
