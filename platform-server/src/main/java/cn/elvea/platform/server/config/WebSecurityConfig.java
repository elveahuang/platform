package cn.elvea.platform.server.config;

import cn.elvea.platform.core.security.jwt.JwtAuthenticationFilter;
import cn.elvea.platform.core.security.jwt.JwtAuthorizationFiler;
import cn.elvea.platform.core.security.jwt.authentication.JwtCaptchaAuthenticationProvider;
import cn.elvea.platform.core.security.jwt.authentication.JwtPasswordAuthenticationProvider;
import cn.elvea.platform.core.security.jwt.authentication.JwtRefreshTokenAuthenticationProvider;
import cn.elvea.platform.core.security.service.JwtSecurityService;
import cn.elvea.platform.core.security.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 安全配置
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final JwtSecurityService securityService;
    private final SecurityUserDetailsService userDetailsService;
    private final JwtCaptchaAuthenticationProvider jwtCaptchaAuthenticationProvider;
    private final JwtPasswordAuthenticationProvider jwtPasswordAuthenticationProvider;
    private final JwtRefreshTokenAuthenticationProvider jwtRefreshTokenAuthenticationProvider;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             JwtSecurityService securityService,
                             SecurityUserDetailsService userDetailsService,
                             JwtCaptchaAuthenticationProvider jwtCaptchaAuthenticationProvider,
                             JwtPasswordAuthenticationProvider jwtPasswordAuthenticationProvider,
                             JwtRefreshTokenAuthenticationProvider jwtRefreshTokenAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
        this.userDetailsService = userDetailsService;
        this.jwtCaptchaAuthenticationProvider = jwtCaptchaAuthenticationProvider;
        this.jwtPasswordAuthenticationProvider = jwtPasswordAuthenticationProvider;
        this.jwtRefreshTokenAuthenticationProvider = jwtRefreshTokenAuthenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.jwtCaptchaAuthenticationProvider)
                .authenticationProvider(this.jwtPasswordAuthenticationProvider)
                .authenticationProvider(this.jwtRefreshTokenAuthenticationProvider)
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .mvcMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/favicon.ico", "/static/**", "/webjars/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/swagger-ui/**", "/swagger.html", "/api-docs", "/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.jwtAuthorizationFiler(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(this.authenticationManager(), securityService);
    }

    @Bean
    public JwtAuthorizationFiler jwtAuthorizationFiler() {
        return new JwtAuthorizationFiler(this.securityService, this.userDetailsService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
