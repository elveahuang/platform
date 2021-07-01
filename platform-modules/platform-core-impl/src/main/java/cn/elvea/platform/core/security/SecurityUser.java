package cn.elvea.platform.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * SecurityUser
 *
 * @author elvea
 * @since 0.0.1
 */
public class SecurityUser extends User implements cn.elvea.platform.commons.user.User {

    private Long id;

    public SecurityUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
