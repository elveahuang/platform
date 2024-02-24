package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.service.CachingEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageTemplateService extends CachingEntityService<MessageTemplateEntity, Long> {

    MessageTemplateEntity findByType(Long typeId, Long templateTypeId);

}
