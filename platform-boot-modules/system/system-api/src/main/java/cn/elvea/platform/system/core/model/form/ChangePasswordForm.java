package cn.elvea.platform.system.core.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {
    @NotEmpty
    private String originalPassword;
    @NotEmpty
    private String newPassword;
}
