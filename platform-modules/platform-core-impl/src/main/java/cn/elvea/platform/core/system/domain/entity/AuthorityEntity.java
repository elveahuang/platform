package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

/**
 * 权限
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_authority")
public class AuthorityEntity extends BaseEntity {
    /**
     * Parent ID
     */
    private Long parentId;
    /**
     * 权限编号
     */
    private String code;
    /**
     * 权限文本
     */
    private String label;
    /**
     * 权限标题
     */
    private String title;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 权限类型
     */
    private Integer type_;
    /**
     * 排序序号
     */
    private Integer index_;
    /**
     * 启用状态
     */
    private Boolean active;
}
