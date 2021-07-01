package cn.elvea.platform.core.security;

import cn.elvea.platform.core.security.SecurityUser;
import cn.elvea.platform.core.system.domain.dto.UserDto;
import cn.elvea.platform.core.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * JwtUserDetailsService
 *
 * @author elvea
 * @see UserDetailsService
 * @since 0.0.1
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        return new SecurityUser(user.getId(), user.getUsername(), user.getPassword(), authorities);
    }

}
