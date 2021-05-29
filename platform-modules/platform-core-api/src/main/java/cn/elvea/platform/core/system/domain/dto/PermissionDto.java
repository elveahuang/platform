package cn.elvea.platform.core.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Permission
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionDto implements Serializable {
    // 权限编号
    private String code;
    // 权限标题
    private String title;
    // 权限描述
    private String description;
}
