package cn.elvea.platform.commons.redis.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * CacheBean
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class User implements Serializable {
    public Long id;
    public String username;
    public String description;
}
