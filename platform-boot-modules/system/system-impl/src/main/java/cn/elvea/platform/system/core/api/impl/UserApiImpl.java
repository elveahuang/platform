package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.extensions.captcha.service.CaptchaService;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.SecurityUtils;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.converter.AuthorityConverter;
import cn.elvea.platform.system.core.model.converter.RoleConverter;
import cn.elvea.platform.system.core.model.converter.UserConverter;
import cn.elvea.platform.system.core.model.dto.UserCheckEmailDto;
import cn.elvea.platform.system.core.model.dto.UserCheckUsernameDto;
import cn.elvea.platform.system.core.model.dto.UserInfoDto;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import cn.elvea.platform.system.core.model.entity.AuthorityEntity;
import cn.elvea.platform.system.core.model.entity.RoleEntity;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.model.form.*;
import cn.elvea.platform.system.core.model.vo.UserForgetPasswordVo;
import cn.elvea.platform.system.core.service.AuthorityService;
import cn.elvea.platform.system.core.service.RoleService;
import cn.elvea.platform.system.core.service.UserService;
import com.lark.oapi.core.utils.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static cn.elvea.platform.commons.core.enums.ResponseCodeEnum.USER__EMAIL_NOT_AVAILABLE;
import static cn.elvea.platform.commons.core.enums.ResponseCodeEnum.USER__USERNAME_NOT_AVAILABLE;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    private final CaptchaService captchaService;

    private final PasswordEncoder passwordEncoder;

    /**
     * @see UserApi#getUserInfo(String)
     */
    @Override
    public UserInfoDto getUserInfo(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getUserInfoDto(entity);
    }

    /**
     * @see UserApi#findByUsername(String)
     */
    @Override
    public UserLoginDto findByUsername(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getUserLoginDto(entity);
    }

    /**
     * @see UserApi#findByMobile(String, String)
     */
    @Override
    public UserLoginDto findByMobile(String mobileCountryCode, String mobileNumber) {
        UserEntity entity = userService.findByMobile(mobileCountryCode, mobileNumber);
        return getUserLoginDto(entity);
    }

    /**
     * @see UserApi#findByEmail(String)
     */
    @Override
    public UserLoginDto findByEmail(String email) {
        UserEntity entity = userService.findByEmail(email);
        return getUserLoginDto(entity);
    }

    private UserInfoDto getUserInfoDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        UserInfoDto user = UserConverter.INSTANCE.entity2UserInfoDto(entity);

        // 查询用户所有权限和角色信息
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        List<RoleEntity> roleEntityList = roleService.findByUserId(user.getId());
        // 合并权限和角色统一为权限
        List<String> roles = Lists.newArrayList();
        List<String> authorities = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            authorities.addAll(authorityEntityList.stream().map(AuthorityEntity::getCode).toList());
        }
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            roles.addAll(roleEntityList.stream().map(RoleEntity::getCode).toList());
            authorities.addAll(roles);
        }
        user.setAuthorities(CollectionUtils.isNotEmpty(authorityEntityList) ? authorities : Collections.emptyList());
        user.setRoles(CollectionUtils.isNotEmpty(roleEntityList) ? roles : Collections.emptyList());

        return user;
    }

    private UserLoginDto getUserLoginDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        UserLoginDto user = UserConverter.INSTANCE.entity2UserLoginDto(entity);
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            user.setAuthorities(AuthorityConverter.INSTANCE.entityListToDtoList(authorityEntityList));
        }
        List<RoleEntity> roleEntityList = roleService.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            user.setRoles(RoleConverter.INSTANCE.entityListToDtoList(roleEntityList));
        }
        return user;
    }

    /**
     * @see UserApi#register(UserRegisterForm)
     */
    @Override
    public R<?> register(UserRegisterForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue()).build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测用户名是否可用
        if (userService.checkUsername(UserCheckUsernameDto.builder().username(form.getUsername()).build())) {
            return R.fail(USER__USERNAME_NOT_AVAILABLE);
        }
        // 检测邮箱是否可用
        if (userService.checkEmail(UserCheckEmailDto.builder().email(form.getEmail()).build())) {
            return R.fail(USER__EMAIL_NOT_AVAILABLE);
        }
        UserEntity userEntity = UserEntity.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .build();
        userService.save(userEntity);
        return R.success();
    }

    /**
     * @see UserApi#changePassword(ChangePasswordForm)
     */
    @Override
    public R<?> changePassword(ChangePasswordForm changePasswordForm) {
        String userName = SecurityUtils.getUsername();
        UserEntity userEntity = userService.findByUsername(userName);
        if (ObjectUtils.isEmpty(userEntity) || !passwordEncoder.matches(changePasswordForm.getOriginalPassword(), userEntity.getPassword())) {
            return R.error();
        }
        userEntity.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userService.updateById(userEntity);
        return R.success();
    }

    /**
     * @see UserApi#updateAccount(UserAccountForm)
     */
    @Override
    public R<?> updateAccount(UserAccountForm userAccountForm) {
        String userName = SecurityUtils.getUsername();
        UserEntity userEntity = userService.findByUsername(userName);
        if (ObjectUtils.isEmpty(userEntity)) {
            return R.error();
        }
        userEntity.setDisplayName(userAccountForm.getDisplayName());
        userService.updateById(userEntity);
        return R.success();
    }

    /**
     * @see UserApi#forgotPassword(ForgotPasswordForm)
     */
    @Override
    public R<UserForgetPasswordVo> forgotPassword(ForgotPasswordForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .clearAfterCheck(false)
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        UserEntity userEntity = userService.findByEmail(form.getEmail());
        if (userEntity != null) {
            return R.success(UserForgetPasswordVo.builder().email(userEntity.getEmail()).username(userEntity.getUsername()).build());
        }
        return R.error();
    }

    /**
     * @see UserApi#resetPassword(ResetPasswordForm)
     */
    @Override
    public R<?> resetPassword(ResetPasswordForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .clearAfterCheck(true)
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        UserEntity userEntity = userService.findByEmail(form.getEmail());
        if (userEntity != null) {
            userEntity.setPassword(passwordEncoder.encode(form.getPassword()));
            userService.updateById(userEntity);
            return R.success();
        }
        return R.error();
    }

    /**
     * @see UserApi#logout()
     */
    @Override
    public R<?> logout() {
        if (!SecurityUtils.isAnonymous()) {
            String userName = SecurityUtils.getUsername();
            log.info("Cur username - [{}}]", userName);
        }
        return R.success();
    }

}
