package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.system.message.enums.MessageStatusEnum;
import cn.elvea.platform.system.message.model.entity.MessageEntity;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageService extends EntityService<MessageEntity, Long> {

    /**
     * 根据发送状态获取消息记录
     */
    List<MessageEntity> findByStatus(List<MessageStatusEnum> statusList);

}
