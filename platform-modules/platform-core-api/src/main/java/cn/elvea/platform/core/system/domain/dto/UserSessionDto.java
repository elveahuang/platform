package cn.elvea.platform.core.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * UserSession
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSessionDto implements Serializable {
    // 会话ID
    private String sessionId;
    // 实体ID
    private String entityId;
    // 用户名
    private String username;
    // 登录主机
    private String host;
    // 登录设备
    private String device;
    // 最后访问时间
    private Date lastAccessDatetime;
    // 开始时间
    private Date startDatetime;
    // 结束时间
    private Date endDatetime;
}
