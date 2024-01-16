package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.core.model.dto.ConfigDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ConfigApi {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
