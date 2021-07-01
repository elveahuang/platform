package cn.elvea.platform.server.config;

import cn.elvea.platform.core.security.CustomAccessDeniedHandler;
import cn.elvea.platform.core.security.CustomAuthenticationEntryPoint;
import cn.elvea.platform.core.security.CustomUserDetailsService;
import cn.elvea.platform.core.security.authentication.*;
import cn.elvea.platform.core.security.enums.SecurityGrantTypeEnum;
import cn.elvea.platform.core.security.filter.JwtAuthenticationFilter;
import cn.elvea.platform.core.security.filter.JwtAuthorizationFiler;
import cn.elvea.platform.core.security.jwt.JwtAuthenticationConverter;
import cn.elvea.platform.core.security.jwt.JwtSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 认证和权限基础配置
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =================================================================================================================
    // Providers
    // =================================================================================================================

    @Bean
    public JwtAccessTokenAuthenticationProvider jwtAccessTokenAuthenticationProvider(
            CustomUserDetailsService userDetailsService) {
        return new JwtAccessTokenAuthenticationProvider(userDetailsService);
    }

    @Bean
    public JwtCaptchaAuthenticationProvider jwtCaptchaAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        return new JwtCaptchaAuthenticationProvider(userDetailsService);
    }

    @Bean
    public JwtPasswordAuthenticationProvider jwtPasswordAuthenticationProvider(
            CustomUserDetailsService userDetailsService,
            JwtSecurityService jwtSecurityService,
            PasswordEncoder passwordEncoder
    ) {
        return new JwtPasswordAuthenticationProvider(userDetailsService, passwordEncoder, jwtSecurityService);
    }

    @Bean
    public JwtRefreshTokenAuthenticationProvider jwtRefreshTokenAuthenticationProvider(
            CustomUserDetailsService userDetailsService,
            JwtSecurityService jwtSecurityService
    ) {
        return new JwtRefreshTokenAuthenticationProvider(userDetailsService, jwtSecurityService);
    }

    @Bean
    public JwtSsoAuthenticationProvider jwtSsoAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        return new JwtSsoAuthenticationProvider(userDetailsService);
    }

    @Bean
    public JwtWeChatAuthenticationProvider jwtWeChatAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        return new JwtWeChatAuthenticationProvider(userDetailsService);
    }

    @Bean
    public JwtWeComAuthenticationProvider jwtWeComAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        return new JwtWeComAuthenticationProvider(userDetailsService);
    }

    // =================================================================================================================
    // Converters
    // =================================================================================================================

    @Bean
    public JwtAccessTokenAuthenticationConverter jwtAccessTokenAuthenticationConverter(JwtSecurityService jwtSecurityService) {
        return new JwtAccessTokenAuthenticationConverter(jwtSecurityService);
    }

    @Bean
    public JwtCaptchaAuthenticationConverter jwtCaptchaAuthenticationConverter() {
        return new JwtCaptchaAuthenticationConverter();
    }

    @Bean
    public JwtPasswordAuthenticationConverter jwtPasswordAuthenticationConverter() {
        return new JwtPasswordAuthenticationConverter();
    }

    @Bean
    public JwtRefreshTokenAuthenticationConverter jwtRefreshTokenAuthenticationConverter(JwtSecurityService jwtSecurityService) {
        return new JwtRefreshTokenAuthenticationConverter(jwtSecurityService);
    }

    @Bean
    public JwtSsoAuthenticationConverter jwtSsoAuthenticationConverter() {
        return new JwtSsoAuthenticationConverter();
    }

    @Bean
    public JwtWeChatAuthenticationConverter jwtWeChatAuthenticationConverter() {
        return new JwtWeChatAuthenticationConverter();
    }

    @Bean
    public JwtWeComAuthenticationConverter jwtWeComAuthenticationConverter() {
        return new JwtWeComAuthenticationConverter();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(
            JwtCaptchaAuthenticationConverter jwtCaptchaAuthenticationConverter,
            JwtPasswordAuthenticationConverter jwtPasswordAuthenticationConverter,
            JwtRefreshTokenAuthenticationConverter jwtRefreshTokenAuthenticationConverter,
            JwtSsoAuthenticationConverter jwtSsoAuthenticationConverter,
            JwtWeChatAuthenticationConverter jwtWeChatAuthenticationConverter,
            JwtWeComAuthenticationConverter jwtWeComAuthenticationConverter
    ) {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.addConverter(SecurityGrantTypeEnum.CAPTCHA.getValue(), jwtCaptchaAuthenticationConverter);
        converter.addConverter(SecurityGrantTypeEnum.PASSWORD.getValue(), jwtPasswordAuthenticationConverter);
        converter.addConverter(SecurityGrantTypeEnum.REFRESH_TOKEN.getValue(), jwtRefreshTokenAuthenticationConverter);
        converter.addConverter(SecurityGrantTypeEnum.SSO.getValue(), jwtSsoAuthenticationConverter);
        converter.addConverter(SecurityGrantTypeEnum.WECHAT.getValue(), jwtWeChatAuthenticationConverter);
        converter.addConverter(SecurityGrantTypeEnum.WECOM.getValue(), jwtWeComAuthenticationConverter);
        return converter;
    }

    // =================================================================================================================
    // Converters
    // =================================================================================================================

    @Bean
    public AuthenticationManager authenticationManager(
            JwtAccessTokenAuthenticationProvider jwtAccessTokenAuthenticationProvider,
            JwtCaptchaAuthenticationProvider jwtCaptchaAuthenticationProvider,
            JwtPasswordAuthenticationProvider jwtPasswordAuthenticationProvider,
            JwtRefreshTokenAuthenticationProvider jwtRefreshTokenAuthenticationProvider,
            JwtSsoAuthenticationProvider jwtSsoAuthenticationProvider,
            JwtWeChatAuthenticationProvider jwtWeChatAuthenticationProvider,
            JwtWeComAuthenticationProvider jwtWeComAuthenticationProvider
    ) {
        return new ProviderManager(
                jwtAccessTokenAuthenticationProvider,
                jwtCaptchaAuthenticationProvider,
                jwtPasswordAuthenticationProvider,
                jwtRefreshTokenAuthenticationProvider,
                jwtSsoAuthenticationProvider,
                jwtWeChatAuthenticationProvider,
                jwtWeComAuthenticationProvider
        );
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtAuthenticationConverter jwtAuthenticationConverter,
            JwtSecurityService jwtSecurityService) {
        return new JwtAuthenticationFilter(authenticationManager, jwtAuthenticationConverter, jwtSecurityService);
    }

    @Bean
    public JwtAuthorizationFiler jwtAuthorizationFiler(
            AuthenticationManager authenticationManager,
            JwtAccessTokenAuthenticationConverter jwtAccessTokenAuthenticationConverter) {
        return new JwtAuthorizationFiler(authenticationManager, jwtAccessTokenAuthenticationConverter);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

}
