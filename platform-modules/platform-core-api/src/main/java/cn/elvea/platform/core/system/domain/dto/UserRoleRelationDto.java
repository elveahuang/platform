package cn.elvea.platform.core.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * UserRole
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleRelationDto implements Serializable {
    // 实体ID
    private Long entityId;
    // 权限ID
    private Long permissionId;
    // 关联时间
    private Timestamp createdAt;
    // 关联人
    private Long createdBy;
}
