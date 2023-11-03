package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import cn.elvea.platform.system.message.request.NoticeSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface NoticeService extends EntityService<NoticeEntity, Long> {

    /**
     * 获取指定用户所有的消息通知
     */
    Page<NoticeEntity> findByUserId(NoticeSearchRequest request);

}
