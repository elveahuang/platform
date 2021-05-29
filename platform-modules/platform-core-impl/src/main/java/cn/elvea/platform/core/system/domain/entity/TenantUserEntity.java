package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * 租户用户
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_tenant_user")
public class TenantUserEntity extends BaseEntity {
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 全名
     */
    private String name;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 入职日期
     */
    private LocalDate entryDate;
    /**
     * 离职日期
     */
    private LocalDate resignationDate;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 数据来源
     */
    private Integer source;
    /**
     * 启用状态
     */
    private Boolean active;
}
