package cn.elvea.platform.core.system.domain.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 客户端
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClientDto implements Serializable {
    /**
     * ID
     */
    private Long id;
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
}
