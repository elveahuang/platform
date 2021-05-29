package cn.elvea.platform.core.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UserSearchParam
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchParam implements Serializable {

    private String keyword;

    private String username;

    private Boolean usernameExactMatch;

    private String nickname;

    private Boolean nicknameExactMatch;

}
