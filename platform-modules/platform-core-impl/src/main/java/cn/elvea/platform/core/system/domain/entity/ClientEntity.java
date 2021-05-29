package cn.elvea.platform.core.system.domain.entity;

import cn.elvea.platform.persistence.domain.BaseEntity;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

/**
 * ClientEntity
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table("sys_client")
public class ClientEntity extends BaseEntity {
    /**
     * Client ID
     */
    private String clientId;
    /**
     * Client Secret
     */
    private String clientSecret;
    /**
     * Authorization Grant Type
     */
    private String authorizationGrantTypes;
    /**
     * Client Authentication Method
     */
    private String clientAuthenticationMethods;
    /**
     * Redirect Uri
     */
    private String redirectUris;
    /**
     * Score
     */
    private String scopes;
    /**
     * 用户状态
     */
    private String description;
    /**
     * 启用状态
     */
    private Boolean active;
}
