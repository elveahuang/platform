package cn.elvea.platform.system.core.api;

import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.form.*;
import cn.elvea.platform.system.core.model.vo.UserForgetPasswordVo;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserApi {

    UserInfoDto getUserInfo(String username);

    UserLoginDto findByUsername(String username);

    UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber);

    UserLoginDto findByEmail(String email);

    R<?> register(UserRegisterForm userRegisterForm);

    R<?> changePassword(ChangePasswordForm changePasswordForm);

    R<?> updateAccount(UserAccountForm userAccountForm);

    R<UserForgetPasswordVo> forgotPassword(ForgotPasswordForm userRegisterForm);

    R<?> resetPassword(ResetPasswordForm userRegisterForm);

    R<?> logout();

}
