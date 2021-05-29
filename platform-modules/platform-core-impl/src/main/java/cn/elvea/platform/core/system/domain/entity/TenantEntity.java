package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 租户
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_tenant")
public class TenantEntity extends BaseEntity {
    /**
     * 编号
     */
    private String code;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String description;
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
