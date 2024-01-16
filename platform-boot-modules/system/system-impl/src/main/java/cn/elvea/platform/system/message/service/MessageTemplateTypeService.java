package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageTemplateTypeService extends CachingEntityService<MessageTemplateTypeEntity, Long> {

    MessageTemplateTypeEntity findByCode(String key);

}
