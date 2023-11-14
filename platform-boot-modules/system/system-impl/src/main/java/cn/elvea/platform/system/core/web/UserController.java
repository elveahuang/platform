package cn.elvea.platform.system.core.web;

import cn.elvea.platform.commons.core.annotations.Anonymous;
import cn.elvea.platform.commons.core.annotations.Authenticated;
import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.commons.constants.SystemMappingConstants;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.form.ChangePasswordForm;
import cn.elvea.platform.system.core.model.form.ForgotPasswordForm;
import cn.elvea.platform.system.core.model.form.ResetPasswordForm;
import cn.elvea.platform.system.core.model.form.UserRegisterForm;
import cn.elvea.platform.system.core.model.vo.UserForgetPasswordVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "UserController", description = "用户控制器")
public class UserController {

    private final UserApi userApi;

    @Authenticated
    @Operation(summary = "获取当前用户信息")
    @ApiResponse(description = "获取当前用户信息")
    @GetMapping(SystemMappingConstants.API_V1__USER__INFO)
    public R<UserInfoDto> user() {
        String curUsername = SecurityUtils.getUsername();
        return R.success(this.userApi.getUserInfo(curUsername));
    }

    @Anonymous
    @OperationLog("用户注册")
    @Operation(summary = "用户注册")
    @ApiResponse(description = "用户注册")
    @PostMapping(SystemMappingConstants.API_V1__USER__REGISTER)
    public R<?> register(@Valid UserRegisterForm form) {
        return userApi.register(form);
    }

    @Anonymous
    @OperationLog("退出登录")
    @Operation(summary = "退出登录")
    @ApiResponse(description = "退出登录")
    @PostMapping(SystemMappingConstants.API_V1__USER__LOGOUT)
    public R<?> logout() {
        return userApi.logout();
    }

    @Anonymous
    @Operation(summary = "忘记密码")
    @ApiResponse(description = "忘记密码")
    @PostMapping(SystemMappingConstants.API_V1__USER__FORGOT_PASSWORD)
    public R<UserForgetPasswordVo> forgotPassword(@Valid ForgotPasswordForm form) {
        return userApi.forgotPassword(form);
    }

    @Anonymous
    @Operation(summary = "重置密码")
    @ApiResponse(description = "重置密码")
    @PostMapping(SystemMappingConstants.API_V1__USER__RESET_PASSWORD)
    public R<?> resetPassword(@Valid ResetPasswordForm form) {
        return userApi.resetPassword(form);
    }

    @Authenticated
    @Operation(summary = "修改密码")
    @ApiResponse(description = "修改密码")
    @PostMapping(SystemMappingConstants.API_V1__USER__CHANGE_PASSWORD)
    public R<?> changePassword(@Valid ChangePasswordForm form) {
        return userApi.changePassword(form);
    }

}
