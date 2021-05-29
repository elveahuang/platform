package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * 用户
 *
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_user")
public class UserEntity extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机国家区号
     */
    private String mobileCountryCode;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 全名
     */
    private String name;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 证件类型
     */
    private String idCardType;
    /**
     * 证件号码
     */
    private String idCardNo;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 备注
     */
    private String description;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 启用状态
     */
    private Boolean active;
}
