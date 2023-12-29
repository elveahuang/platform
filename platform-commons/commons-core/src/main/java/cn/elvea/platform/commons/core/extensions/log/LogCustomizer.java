package cn.elvea.platform.commons.core.extensions.log;

/**
 * @author elvea
 * @since 0.0.1
 */
@FunctionalInterface
public interface LogCustomizer {

    /**
     * 定制日志服务管理器
     *
     * @param manager {@link LogManager}
     */
    void customize(LogManager manager);

}
