package cn.elvea.platform.core.system.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * User
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto implements Serializable {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户名
     */
    private String displayName;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户状态
     */
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
    /**
     * 删除时间
     */
    private String deletedAt;
    /**
     * 删除人
     */
    private Long deletedBy;
    /**
     * 用户角色ID
     */
    private List<Long> roleIds = Collections.emptyList();
    /**
     * 用户角色
     */
    private List<RoleDto> roles = Collections.emptyList();
}
