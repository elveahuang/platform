package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * 用户会话记录
 *
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_user_session")
public class UserSessionEntity extends BaseEntity {
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 会话ID
     */
    private String sessionId;
    /**
     * 登录主机
     */
    private String host;
    /**
     * 登录设备
     */
    private String device;
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 客户端版本
     */
    private String clientVersion;
    /**
     * 登录平台
     */
    private String platform;
    /**
     * 会话开始时间
     */
    private LocalDateTime startDatetime;
    /**
     * 最后访问时间
     */
    private LocalDateTime lastAccessDatetime;
    /**
     * 会话结束时间
     */
    private LocalDateTime endDatetime;
    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    @CreatedBy
    private Long createdBy;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    @LastModifiedBy
    private Long lastModifiedBy;
}
