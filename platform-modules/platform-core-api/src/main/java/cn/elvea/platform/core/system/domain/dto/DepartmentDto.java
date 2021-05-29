package cn.elvea.platform.core.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Department
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentDto implements Serializable {
    // 部门编号
    private String code;
    // 部门标题
    private String title;
    // 部门描述
    private String description;
    // 实体状态
    private String status;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifiedAt;
    /**
     * 最后修改人
     */
    private Long lastModifiedBy;
}
