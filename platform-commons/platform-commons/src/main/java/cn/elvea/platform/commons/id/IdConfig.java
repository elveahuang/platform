package cn.elvea.platform.commons.id;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * IdWorkerConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class IdConfig implements Serializable {
    /**
     * 缓存键值
     */
    public final static String CACHE_KEY_ID_WORKER = "ID_WORKER";
    /**
     * 主键生成器的初始策略-自动生成
     */
    public final static String ID_WORKER_TYPE_AUTO = "auto";
    /**
     * 主键生成器的初始策略-手工指定
     */
    public final static String ID_WORKER_TYPE_MANUAL = "manual";
    /**
     * 主键生成器的初始策略
     */
    private String type = ID_WORKER_TYPE_AUTO;
    /**
     * 应用名称
     */
    private String applicationName = "server";
    /**
     * 应用端口
     */
    private String applicationPort = "";
    /**
     * PID
     */
    private String applicationPid = "";
    /**
     * 数据中心ID
     */
    private Long datacenterId = 1L;
    /**
     * 工作机器ID
     */
    private Long workerId = 1L;
}
