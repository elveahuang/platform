package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.system.message.model.entity.MessageContentEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageContentService extends EntityService<MessageContentEntity, Long> {

    List<MessageContentEntity> findByMessage(Long messageId);

    void success(Long id, String resp);

    void fail(Long id, String resp);

    void fail(Long id, String resp, String exception);

}
