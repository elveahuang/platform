package cn.elvea.platform.system.config.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.config.model.entity.ConfigEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
