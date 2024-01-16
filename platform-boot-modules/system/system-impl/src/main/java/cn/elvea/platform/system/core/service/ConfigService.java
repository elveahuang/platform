package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.core.model.entity.ConfigEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface ConfigService extends CachingEntityService<ConfigEntity, Long> {

    ConfigEntity getConfigByKey(String key);

}
