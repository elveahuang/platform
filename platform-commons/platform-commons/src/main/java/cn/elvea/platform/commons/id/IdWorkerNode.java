package cn.elvea.platform.commons.id;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工作机器
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class IdWorkerNode implements Serializable {
    private String applicationPid;
    private String applicationName;
    private String applicationPort;
    private String ipAddress;
    private Long datacenterId;
    private Long workerId;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
