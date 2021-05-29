package cn.elvea.platform.commons.user;

import java.io.Serializable;

/**
 * User
 *
 * @author elvea
 * @since 0.0.1
 */
public interface User extends Serializable {

    /**
     * ID
     */
    Long getId();

    /**
     * 用户名
     */
    String getUsername();

}
