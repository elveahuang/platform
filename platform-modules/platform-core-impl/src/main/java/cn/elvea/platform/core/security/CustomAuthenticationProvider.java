package cn.elvea.platform.core.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * SecurityAuthenticationProvider
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    protected final CustomUserDetailsService userDetailsService;

    protected CustomAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 获取用户详情
     *
     * @param username       用户名
     * @param authentication {@link UsernamePasswordAuthenticationToken}
     * @return {@link UserDetails}
     */
    @Override
    protected final UserDetails retrieveUser(
            String username,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {
        try {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
            if (loadedUser == null) {
                throw new BadCredentialsException("auth.invalid.username");
            }
            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("auth.invalid.username");
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * 在登录认证时，对用户输入的密码，或者用户状态等信息进行检查。
     *
     * @param userDetails    {@link UserDetails}
     * @param authentication {@link Authentication}
     */
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication
    ) throws AuthenticationException {
        //
    }

    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

}
