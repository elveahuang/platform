package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.form.ChangePasswordForm;
import cn.elvea.platform.system.core.model.form.ForgotPasswordForm;
import cn.elvea.platform.system.core.model.form.ResetPasswordForm;
import cn.elvea.platform.system.core.model.form.UserRegisterForm;
import cn.elvea.platform.system.core.model.vo.UserForgetPasswordVo;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface UserApi {

    UserInfoDto getUserInfo(String username);

    UserLoginDto findByUsername(String username);

    UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber);

    UserLoginDto findByEmail(String email);

    R<?> register(UserRegisterForm userRegisterForm);

    R<?> changePassword(ChangePasswordForm changePasswordForm);

    R<UserForgetPasswordVo> forgotPassword(ForgotPasswordForm userRegisterForm);

    R<?> resetPassword(ResetPasswordForm userRegisterForm);

    R<?> logout();

}
