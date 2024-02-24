package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.service.EntityService;
import cn.elvea.platform.system.message.model.entity.NoticeEntity;
import cn.elvea.platform.system.message.request.NoticeSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface NoticeService extends EntityService<NoticeEntity, Long> {

    /**
     * 获取当前登录用户的系统通知列表
     */
    Page<NoticeEntity> findMyNoticeByUserId(NoticeSearchRequest request);

    /**
     * 获取指定用户所有的消息通知
     */
    Page<NoticeEntity> findByUserId(NoticeSearchRequest request);

}
